/*
 * Copyright (c) 2014, 2019 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package daggerok.logs.payment;

import daggerok.logs.event.PaymentEvent;
import daggerok.logs.interceptor.Logged;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * Bean that fires DEBIT and CREDIT payment events selection.
 * Check server log output for event handling output.
 */
@Data
@Slf4j
public class PaymentBean implements Serializable {

  private static final long serialVersionUID = 7130389273118012929L;
  private static final int DEBIT = 1;
  private static final int CREDIT = 2;

  @Inject
  @Credit
  Event<PaymentEvent> creditEvent;

  @Inject
  @Debit
  Event<PaymentEvent> debitEvent;

  private int paymentOption ;
  private BigDecimal value;
  private Date datetime;

  @PostConstruct
  public void init() {
    paymentOption = System.currentTimeMillis() / 234 % 2 == 0 ? DEBIT : CREDIT;
    value = System.currentTimeMillis() / 123 % 2 == 0 ? BigDecimal.ONE : BigDecimal.TEN;
  }

  /**
   * Fires a payment event.
   *
   * @return the response page location
   */
  @Logged
  public String pay() {
    this.setDatetime(Calendar.getInstance().getTime());
    switch (paymentOption) {
      case DEBIT:
        PaymentEvent debitPayload = new PaymentEvent();
        debitPayload.setPaymentType("Debit");
        debitPayload.setValue(value);
        debitPayload.setDatetime(datetime);
        debitEvent.fire(debitPayload);
        break;
      case CREDIT:
        PaymentEvent creditPayload = new PaymentEvent();
        creditPayload.setPaymentType("Credit");
        creditPayload.setValue(value);
        creditPayload.setDatetime(datetime);
        creditEvent.fire(creditPayload);
        break;
      default:
        log.error("Invalid payment option!");
    }
    return "response";
  }

  @Logged
  public void reset() {
    setPaymentOption(DEBIT);
    setValue(BigDecimal.ZERO);
  }
}

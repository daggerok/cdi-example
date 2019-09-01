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
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import static daggerok.logs.payment.PaymentBean.Type.CREDIT;
import static daggerok.logs.payment.PaymentBean.Type.DEBIT;

/**
 * Bean that fires DEBIT and CREDIT payment events selection.
 * Check server log output for event handling output.
 */
@Slf4j
@ToString
public class PaymentBean {

  @RequiredArgsConstructor
  public enum Type {
    CREDIT(1), DEBIT(2);
    @Getter public final int option;
  }

  @Inject
  @Credit
  private Event<PaymentEvent> creditEvent;

  @Inject
  @Debit
  private Event<PaymentEvent> debitEvent;

  private Type paymentType ;
  private BigDecimal value;
  private Date datetime;

  @PostConstruct
  public void init() {
    paymentType = System.currentTimeMillis() / 234 % 2 == 0 ? DEBIT : CREDIT;
    value = System.currentTimeMillis() / 123 % 2 == 0 ? BigDecimal.ONE : BigDecimal.TEN;
  }

  /**
   * Fires a payment event.
   *
   * @return the response page location
   */
  @Logged
  public String pay() {
    datetime = Calendar.getInstance().getTime();
    switch (paymentType) {
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
    paymentType = DEBIT;
    value = BigDecimal.ZERO;
  }
}

/*
 * Copyright (c) 2014, 2019 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package daggerok.logs.listener;

import daggerok.interceptors.logging.Logged;
import daggerok.logs.event.PaymentEvent;
import daggerok.logs.payment.Credit;
import daggerok.logs.payment.Debit;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.Dependent;
import javax.enterprise.event.Observes;
import java.io.Serializable;

/**
 * Handler for the two kinds of PaymentEvent.
 */
@Slf4j
@Logged
@Dependent // comment to see less logs
public class PaymentHandler implements Serializable {

  private static final long serialVersionUID = 2013564481486393525L;

  public PaymentHandler() {
    log.info("PaymentHandler created.");
  }

  public void creditPayment(@Observes @Credit PaymentEvent event) {
    log.info("PaymentHandler - Credit Handler: {}", event);
    // call a specific Credit handler class...
  }

  public void debitPayment(@Observes @Debit PaymentEvent event) {
    log.info("PaymentHandler - Debit Handler: {}", event);
    // call a specific Debit handler class...
  }
}

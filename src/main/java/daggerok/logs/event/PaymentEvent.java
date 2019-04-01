/*
 * Copyright (c) 2014, 2019 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package daggerok.logs.event;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Common payment event that handles Debit and Credit payment types.
 */
@Data
public class PaymentEvent implements Serializable {
  private static final long serialVersionUID = -6407967360613478424L;
  public String paymentType;
  public BigDecimal value;
  public Date datetime;
}

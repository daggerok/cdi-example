/*
 * Copyright (c) 2014, 2019 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package daggerok.logs.interceptor;

import javax.annotation.Priority;
import javax.enterprise.context.Dependent;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

@Logged
@Interceptor
@Dependent
@Priority(Interceptor.Priority.APPLICATION)
public class LoggedInterceptor implements Serializable {

  private static final long serialVersionUID = -2230122751970857993L;

  @AroundInvoke
  public Object logMethodEntry(InvocationContext invocationContext)
      throws Exception {
    System.out.println("Entering method: "
                           + invocationContext.getMethod().getName() + " in class "
                           + invocationContext.getMethod().getDeclaringClass().getName());
    return invocationContext.proceed();
  }
}

/*
 * Copyright 2013-2014 Sergey Ignatov, Alexander Zolotov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.goide.psi.impl;

import com.goide.psi.GoTypeSpec;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public class GoTypeProcessor extends GoScopeProcessorBase {
  public GoTypeProcessor(String requestedName, PsiElement origin, boolean completion) {
    super(requestedName, origin, completion);
  }

  @Override
  protected boolean condition(@NotNull PsiElement element) {
    return !(element instanceof GoTypeSpec);
  }
}

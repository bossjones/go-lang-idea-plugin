// This is a generated file. Not intended for manual editing.
package com.goide.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GoReceiverType extends GoType {

  @Nullable
  GoReceiverType getReceiverType();

  @Nullable
  GoTypeReferenceExpression getTypeReferenceExpression();

  @Nullable
  PsiElement getLparen();

  @Nullable
  PsiElement getMul();

  @Nullable
  PsiElement getRparen();

}

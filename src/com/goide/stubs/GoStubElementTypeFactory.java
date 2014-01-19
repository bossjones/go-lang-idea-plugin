package com.goide.stubs;

import com.goide.psi.GoParamDefinition;
import com.goide.stubs.types.GoConstDefinitionStubElementType;
import com.goide.stubs.types.GoFieldDefinitionStubElementType;
import com.goide.stubs.types.GoFunctionDeclarationStubElementType;
import com.goide.stubs.types.GoImportSpecStubElementType;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

public class GoStubElementTypeFactory {
  @NotNull
  public static IElementType factory(@NotNull String name) {
    if (name.equals("CONST_DEFINITION")) return new GoConstDefinitionStubElementType(name);
    if (name.equals("FIELD_DEFINITION")) return new GoFieldDefinitionStubElementType(name);
    if (name.equals("FUNCTION_DECLARATION")) return new GoFunctionDeclarationStubElementType(name);
    if (name.equals("IMPORT_SPEC")) return new GoImportSpecStubElementType(name);
    if (name.equals("PARAM_DEFINITION")) return GoParamDefinitionStubElementType(name);
    if (name.equals("RECEIVER")) return new GoReveiverStubElementType(name);
    if (name.equals("TYPE_SPEC")) return new GoTypeSpecStubElementType(name);
    if (name.equals("VAR_DEFINITION")) return new GoVarDefinitionStubElementType(name);

    throw new RuntimeException("Unknown element type: " + name);
  }
}
package main
var e = 1 * 2
/**-----
Go file
  PackageDeclaration(main)
    PsiElement(KEYWORD_PACKAGE)('package')
    PsiWhiteSpace(' ')
    PsiElement(IDENTIFIER)('main')
  PsiWhiteSpace('\n')
  VarDeclarationsImpl
    PsiElement(KEYWORD_VAR)('var')
    PsiWhiteSpace(' ')
    VarDeclarationImpl
      LiteralIdentifierImpl
        PsiElement(IDENTIFIER)('e')
      PsiWhiteSpace(' ')
      PsiElement(=)('=')
      PsiWhiteSpace(' ')
      MultiplicativeExpressionImpl
        LiteralExpressionImpl
          LiteralIntegerImpl
            PsiElement(LITERAL_INT)('1')
        PsiWhiteSpace(' ')
        PsiElement(*)('*')
        PsiWhiteSpace(' ')
        LiteralExpressionImpl
          LiteralIntegerImpl
            PsiElement(LITERAL_INT)('2')

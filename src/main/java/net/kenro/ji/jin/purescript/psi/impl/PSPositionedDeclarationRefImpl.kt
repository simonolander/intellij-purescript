package net.kenro.ji.jin.purescript.psi.impl

import com.intellij.lang.ASTNode
import net.kenro.ji.jin.purescript.psi.PSPositionedDeclarationRef

class PSPositionedDeclarationRefImpl(node: ASTNode) : PSPsiElement(node),
    PSPositionedDeclarationRef
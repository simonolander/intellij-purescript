package net.kenro.ji.jin.purescript.psi.impl

import com.intellij.lang.ASTNode
import net.kenro.ji.jin.purescript.psi.PSCaseAlternative

class PSCaseAlternativeImpl(node: ASTNode) : PSPsiElement(node),
    PSCaseAlternative
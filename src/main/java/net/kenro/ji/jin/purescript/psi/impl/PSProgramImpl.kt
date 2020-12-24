package net.kenro.ji.jin.purescript.psi.impl

import com.intellij.lang.ASTNode

class PSProgramImpl(node: ASTNode) : PSPsiElement(node) {
    val module: PSModuleImpl
        get() = this.firstChild as PSModuleImpl
}
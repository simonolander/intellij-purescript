package org.purescript.psi.imports

import com.intellij.testFramework.fixtures.BasePlatformTestCase
import junit.framework.TestCase
import org.purescript.file.PSFile


class PSImportedKindTest : BasePlatformTestCase() {

    fun `test imported kind has correct name`() {
        val file = myFixture.configureByText(
            "Foo.purs",
            """
                module Foo where
                import Bar (kind Qux)
            """.trimIndent()
        ) as PSFile
        val importDecl = file.module.importDeclarations.single()
        val importedKind = importDecl.importList!!.importedItems.single() as PSImportedKind
        TestCase.assertEquals("Qux", importedKind.name)
    }
}

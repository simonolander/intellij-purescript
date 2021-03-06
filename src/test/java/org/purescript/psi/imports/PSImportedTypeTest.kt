package org.purescript.psi.imports

import com.intellij.testFramework.fixtures.BasePlatformTestCase
import junit.framework.TestCase
import org.purescript.file.PSFile


class PSImportedTypeTest : BasePlatformTestCase() {

    fun `test imported type has correct name`() {
        val file = myFixture.configureByText(
            "Foo.purs",
            """
                module Foo where
                import Bar (type (<~>))
            """.trimIndent()
        ) as PSFile
        val importDecl = file.module.importDeclarations.single()
        val importedType = importDecl.importList!!.importedItems.single() as PSImportedType
        TestCase.assertEquals("<~>", importedType.name)
    }
}

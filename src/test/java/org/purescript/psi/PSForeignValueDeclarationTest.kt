package org.purescript.psi

import com.intellij.testFramework.fixtures.BasePlatformTestCase
import org.purescript.file.PSFile

class PSForeignValueDeclarationTest : BasePlatformTestCase() {
    fun `test knows its name`() {
        myFixture.configureByText(
            "Main.purs",
            """
            module Main where
            
            -- | Returns the substrings of the second string separated
            foreign import <caret>split :: Pattern -> String -> Array String
            """.trimIndent()
        ) as PSFile

        val elementAtCaret = myFixture.elementAtCaret as PSForeignValueDeclaration

        assertNotNull( elementAtCaret)
        assertEquals( "split", elementAtCaret.name)
    }
}
package net.kenro.ji.jin.purescript.psi.impl

import net.kenro.ji.jin.purescript.psi.impl.PSPsiElement
import net.kenro.ji.jin.purescript.psi.PSAbs
import net.kenro.ji.jin.purescript.psi.PSAccessor
import net.kenro.ji.jin.purescript.psi.PSArrayLiteral
import net.kenro.ji.jin.purescript.psi.PSBang
import net.kenro.ji.jin.purescript.psi.PSBooleanBinder
import net.kenro.ji.jin.purescript.psi.PSBooleanLiteral
import net.kenro.ji.jin.purescript.psi.PSCaseAlternative
import net.kenro.ji.jin.purescript.psi.PSCase
import net.kenro.ji.jin.purescript.psi.PSClassName
import net.kenro.ji.jin.purescript.psi.PSConstrainedType
import net.kenro.ji.jin.purescript.psi.DeclaresIdentifiers
import net.kenro.ji.jin.purescript.psi.impl.PSIdentifierImpl
import net.kenro.ji.jin.purescript.psi.ContainsIdentifier
import kotlin.collections.MutableMap.MutableEntry
import java.util.stream.Collectors
import net.kenro.ji.jin.purescript.psi.PSConstructor
import net.kenro.ji.jin.purescript.psi.PSDoNotationBind
import net.kenro.ji.jin.purescript.psi.PSDoNotationLet
import net.kenro.ji.jin.purescript.psi.PSDoNotationValue
import net.kenro.ji.jin.purescript.psi.PSExternDataDeclaration
import net.kenro.ji.jin.purescript.psi.PSExternDeclaration
import net.kenro.ji.jin.purescript.psi.PSExternInstanceDeclaration
import net.kenro.ji.jin.purescript.psi.PSFixityDeclaration
import net.kenro.ji.jin.purescript.psi.PSFixity
import net.kenro.ji.jin.purescript.psi.PSForAll
import net.kenro.ji.jin.purescript.psi.PSFunKind
import net.kenro.ji.jin.purescript.psi.PSGenericIdentifier
import net.kenro.ji.jin.purescript.psi.PSGuard
import com.intellij.psi.PsiNamedElement
import com.intellij.util.IncorrectOperationException
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.psi.PsiReferenceBase
import com.intellij.openapi.util.TextRange
import net.kenro.ji.jin.purescript.psi.impl.PSValueDeclarationImpl
import net.kenro.ji.jin.purescript.file.PSFile
import net.kenro.ji.jin.purescript.psi.PSIdentInfix
import net.kenro.ji.jin.purescript.psi.PSIfThenElse
import net.kenro.ji.jin.purescript.psi.PSImplies
import net.kenro.ji.jin.purescript.psi.PSImportModuleName
import net.kenro.ji.jin.purescript.psi.PSJSRaw
import net.kenro.ji.jin.purescript.psi.PSLet
import net.kenro.ji.jin.purescript.psi.PSLocalIdentifier
import net.kenro.ji.jin.purescript.psi.PSModule
import net.kenro.ji.jin.purescript.psi.PSModuleName
import net.kenro.ji.jin.purescript.psi.PSNamedBinder
import net.kenro.ji.jin.purescript.psi.PSNewTypeDeclaration
import net.kenro.ji.jin.purescript.psi.PSNullBinder
import net.kenro.ji.jin.purescript.psi.PSNumberBinder
import net.kenro.ji.jin.purescript.psi.PSNumericLiteral
import net.kenro.ji.jin.purescript.psi.PSObjectBinderField
import net.kenro.ji.jin.purescript.psi.PSObjectBinder
import net.kenro.ji.jin.purescript.psi.PSObjectLiteral
import net.kenro.ji.jin.purescript.psi.PSObjectType
import net.kenro.ji.jin.purescript.psi.PSParens
import net.kenro.ji.jin.purescript.psi.PSPositionedDeclarationRef
import net.kenro.ji.jin.purescript.psi.PSPrefixValue
import net.kenro.ji.jin.purescript.psi.PSProgram
import net.kenro.ji.jin.purescript.psi.impl.PSModuleImpl
import net.kenro.ji.jin.purescript.psi.impl.PSPsiImplUtil
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import net.kenro.ji.jin.purescript.psi.impl.PSProperNameImpl
import net.kenro.ji.jin.purescript.psi.PSQualified
import net.kenro.ji.jin.purescript.psi.PSQualifiedModuleName
import net.kenro.ji.jin.purescript.psi.PSRow
import net.kenro.ji.jin.purescript.psi.PSRowKind
import net.kenro.ji.jin.purescript.psi.PSStar
import net.kenro.ji.jin.purescript.psi.PSStringBinder
import net.kenro.ji.jin.purescript.psi.PSStringLiteral
import net.kenro.ji.jin.purescript.psi.PSTypeAnnotationName
import net.kenro.ji.jin.purescript.psi.PSTypeArgs
import net.kenro.ji.jin.purescript.psi.PSTypeAtom
import net.kenro.ji.jin.purescript.psi.PSTypeClassDeclaration
import net.kenro.ji.jin.purescript.psi.PSTypeConstructor
import net.kenro.ji.jin.purescript.psi.PSTypeDeclaration
import net.kenro.ji.jin.purescript.psi.PSTypeHole
import net.kenro.ji.jin.purescript.psi.PSType
import net.kenro.ji.jin.purescript.psi.PSTypeInstanceDeclaration
import net.kenro.ji.jin.purescript.psi.PSTypeSynonymDeclaration
import net.kenro.ji.jin.purescript.psi.PSTypeVar
import net.kenro.ji.jin.purescript.psi.PSUnaryMinus
import com.intellij.psi.PsiNameIdentifierOwner
import net.kenro.ji.jin.purescript.psi.PSValue
import net.kenro.ji.jin.purescript.psi.PSValueRef
import net.kenro.ji.jin.purescript.psi.PSVar

class PSNamedBinderImpl(node: ASTNode) : PSPsiElement(node), PSNamedBinder
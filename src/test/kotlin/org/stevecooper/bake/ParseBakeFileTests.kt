package org.stevecooper.bake

import org.stevecooper.util.readToString
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import org.junit.Test as test

class ParseBakeFileTests {

    private fun parseRecipe(file: String) {
        val compiler = Compiler()

        val larderFile = "/Larder.txt"
        val larderContent = this.javaClass.getResourceAsStream(larderFile).readToString()
        compiler.readCookbook(larderContent, larderFile)

        val fileContent = this.javaClass.getResourceAsStream(file).readToString()
        Compiler().readCookbook(fileContent, file)
        compiler.readCookbook(larderContent, larderFile)

        val result = compiler.result()

        assertFalse(result.fail())
    }

    @test fun `can parse caramel-sauce recipe`() {
        parseRecipe("/caramel-sauce.bake")
    }

    @test fun `can parse sponge-cake recipe`() {
        parseRecipe("/sponge-cake.bake")
    }

    @test fun `can parse macarons recipe`() {
        parseRecipe("/macarons.bake")
    }

    @test fun `fail on gibberish`() {
        val gibberish = "!!!"
        val result = Compiler().readCookbook(gibberish, "gibberish test")
        assert(result.fail())
    }
}
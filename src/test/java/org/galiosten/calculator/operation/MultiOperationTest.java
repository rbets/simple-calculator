package org.galiosten.calculator.operation;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/*
 * (non-javadoc)
 * 
 * Test case for combinations of different operations.  Combinations will be:
 *       | add   sub   mult   div
 *   ----------------------------
 *   add |  x     x      x     x
 *   sub |  x     x      x     x
 *   mult|  x     x      x     x
 *   div |  x     x      x     x
 */
public class MultiOperationTest {

	@Test
	public void testAddAdd() {

		// the expression will be x = 1 + (1 + 1)
		final Operation addAdd = new Add(1, new Add(1, 1));

		assertThat(addAdd.evaluate(), equalTo(3));
	}

	@Test
	public void testAddSub() {

		// the expression will be x = 1 + (2 - 1)
		final Operation addSub = new Add(1, new Sub(2, 1));

		assertThat(addSub.evaluate(), equalTo(2));
	}

	@Test
	public void testAddMult() {

		// the expression will be x = 1 + (2 * 3)
		final Operation addMult = new Add(1, new Mult(2, 3));

		assertThat(addMult.evaluate(), equalTo(7));
	}

	@Test
	public void testAddDiv() {

		// the expression will be x = 1 + (4 / 2)
		final Operation addDiv = new Add(1, new Div(4, 2));

		assertThat(addDiv.evaluate(), equalTo(3));
	}

	@Test
	public void testSubAdd() {

		// the expression will be x = 2 - (1 + 1)
		final Operation subAdd = new Sub(2, new Add(1, 1));

		assertThat(subAdd.evaluate(), equalTo(0));
	}

	@Test
	public void testSubSub() {

		// the expression will be x = 3 - (2 - 1)
		final Operation subSub = new Sub(3, new Sub(2, 1));

		assertThat(subSub.evaluate(), equalTo(2));
	}

	@Test
	public void testSubMult() {

		// the expression will be x = 7 - (2 * 3)
		final Operation subMult = new Sub(7, new Mult(2, 3));

		assertThat(subMult.evaluate(), equalTo(1));
	}

	@Test
	public void testSubDiv() {

		// the expressionwill be x = 3 - (4 / 2)
		final Operation subDiv = new Sub(3, new Div(4, 2));

		assertThat(subDiv.evaluate(), equalTo(1));
	}

	@Test
	public void testMultAdd() {

		// the expression will be x = 2 * (2 + 3)
		final Operation multAdd = new Mult(2, new Add(2, 3));

		assertThat(multAdd.evaluate(), equalTo(10));
	}

	@Test
	public void testMultSub() {

		// the expression will be x = 2 * (4 - 2)
		final Operation multSub = new Mult(2, new Sub(4, 2));

		assertThat(multSub.evaluate(), equalTo(4));
	}

	@Test
	public void testMultMult() {

		// the expression will be x = 2 * (3 * 4)
		final Operation multMult = new Mult(2, new Mult(3, 4));

		assertThat(multMult.evaluate(), equalTo(24));
	}

	@Test
	public void testMultDiv() {

		// the expression will be x = 2 * (4 / 2)
		final Operation multDiv = new Mult(2, new Div(4, 2));

		assertThat(multDiv.evaluate(), equalTo(4));
	}

	@Test
	public void divAdd() {

		// the expression will be x = 6 / (1 + 2)
		final Operation divAdd = new Div(6, new Add(1, 2));

		assertThat(divAdd.evaluate(), equalTo(2));
	}

	@Test
	public void divSub() {

		// the expression will be x = 6 / (3 - 1)
		final Operation divSub = new Div(6, new Sub(3, 1));

		assertThat(divSub.evaluate(), equalTo(3));
	}

	@Test
	public void divMult() {

		// the expression will be x = 12 / (2 * 3)
		final Operation divMult = new Div(12, new Mult(2, 3));

		assertThat(divMult.evaluate(), equalTo(2));
	}

	@Test
	public void divDiv() {

		// the expression will be x = 6 / (6 / 2)
		final Operation divDiv = new Div(6, new Div(6, 2));

		assertThat(divDiv.evaluate(), equalTo(2));
	}
}

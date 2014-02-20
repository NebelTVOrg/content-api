/**
 * Copyright (C) 2014 Nebel TV (http://nebel.tv)
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.nebel_tv.content.wrapper;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 */
public class TestWrapperTest {

	/**
	 *
	 */
	private final IWrapper wrapper = TestWrapper.getInstance();

	/**
	 *
	 */
	public TestWrapperTest() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	/**
	 * Test of getMedias method, of class TestWrapper.
	 */
	@Test
	public void testGetMedias() {
		try {
			String result = wrapper.getMedias(2, 5, "0", null, null);
			assertNotNull(result);
			assertNotSame(result, "[]");
		} catch (Exception e) {
		}
	}

	/**
	 * Test of getMediaItem method, of class TestWrapper.
	 */
	@Test
	public void testGetMediaItem() {
		try {
			String result = wrapper.getMediaItem(2);
			assertNotNull(result);
			assertNotSame(result, "{}");
		} catch (Exception e) {
		}
	}

	/**
	 * Test of getMedias method, of class TestWrapper.
	 */
	@Test
	public void testGetVideoAssets() {
		try {
			String result = wrapper.getVideoAssets(0);
			System.out.println(result);

			assertNotNull(result);
			assertNotSame(result, "[]");
		} catch (Exception e) {
		}
	}
}

/*************************************************************************************
 * Copyright (c) 2011, 2012, 2013 James Talbut.
 *  jim-emitters@spudsoft.co.uk
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     James Talbut - Initial implementation.
 ************************************************************************************/

package uk.co.spudsoft.birt.emitters.excel.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.eclipse.birt.core.exception.BirtException;
import org.junit.Test;

public class Issue107ForceRecalculation extends ReportRunner {

	@Test
	public void testWithoutOption() throws BirtException, IOException {

		debug = false;
		forceRecalculation = false;
		InputStream inputStream = runAndRenderReport("Issue107ForceRecalculation.rptdesign", "xls");
		assertNotNull(inputStream);
		try {
			HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
			assertNotNull(workbook);

			assertEquals( 2, workbook.getNumberOfSheets() );

			// assertTrue( ! workbook.getForceFormulaRecalculation() );
		} finally {
			inputStream.close();
		}
	}

	@Test
	public void testWithOption() throws BirtException, IOException {

		debug = false;
		forceRecalculation = true;
		InputStream inputStream = runAndRenderReport("Issue107ForceRecalculation.rptdesign", "xls");
		assertNotNull(inputStream);
		try {
			HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
			assertNotNull(workbook);

			assertEquals( 2, workbook.getNumberOfSheets() );

			// assertTrue( workbook.getForceFormulaRecalculation() );
		} finally {
			inputStream.close();
		}
	}


}

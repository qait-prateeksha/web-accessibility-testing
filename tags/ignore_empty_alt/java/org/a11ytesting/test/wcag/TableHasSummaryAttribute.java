/* Copyright 2011 eBay Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *    http://www.apache.org/licenses/LICENSE-2.0
 *    
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.a11ytesting.test.wcag;

import static org.a11ytesting.test.wcag.Shared.SUMMARY;

import org.a11ytesting.filter.TableFilter;
import org.a11ytesting.test.Filter;
import org.a11ytesting.test.HtmlVersion;
import org.a11ytesting.test.Issue;
import org.a11ytesting.test.Issue.Severity;
import org.jsoup.nodes.Element;

/**
 * Rule for table summary attribute presence.
 * 
 * @author dallison
 */
public class TableHasSummaryAttribute extends AbstractPerceivableRule {

	@Override
	public String getRuleName() {
		return "checkTableHasSummaryAttribute";
	}

	@Override
	public Filter getFilter() {
		return new TableFilter();
	}

	/**
	 * Check that data tables are using the summary attribute.
	 * @param table to check
	 * 
	 * @see http://openajax-dev.jongund.webfactional.com/wcag20/rule/3/
	 * 
	 * @return issue or null
	 */
	@Override
	public Issue check(HtmlVersion htmlVersion, Element table) {
		switch (htmlVersion) {
		// summary attribute is not supported by html5
		case HTML5: {
			if (table.hasAttr(SUMMARY)) {
				return new Issue("checkTableHasSummaryAttribute",
						"Check that table summary is not supported by html5",
						Severity.WARNING, table);
			}
			return null;
		}

		default: {
			if (!table.hasAttr(SUMMARY)) {
				// @todo(dallison) Consider checking length
				return new Issue("checkTableHasSummaryAttribute",
						"Check that data table has a summary attribute",
						Severity.ERROR, table);
			}
			return null;
		}
		}
	}
}
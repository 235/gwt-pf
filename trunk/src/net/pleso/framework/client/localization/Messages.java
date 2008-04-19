/*
 * Copyright 2007 Pleso.net
 * 
 * Licensed under the GNU Lesser General Public License, Version 2.1 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.gnu.org/licenses/lgpl.html
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package net.pleso.framework.client.localization;

/**
 * Messages used by the i18n.
 */
public interface Messages extends com.google.gwt.i18n.client.Messages {

	 String loading();
	 String datasource_error();
	 String unknown_error();
	 String error();
	 String no_data();
	 String no_selected_row();
	 String field_is_required(String fieldName);
	 String bad_value_in_field(String fieldName);
	 
	 String delete_confirmation();
	 
	 String pagenumber_from_pagescount(int pageNumber, int pagesCount);
	 
	 // range controls
	 String start_range_field_is_required();
	 String end_range_field_is_required();
	 String startdate_less_or_equal_enddate();
	 String start_float_less_or_equal_end_float();
	 String starttime_less_or_equal_enddate();
	 
	 String asyncerror_delete();
	 String asyncerror_save();
	 String asyncerror_loadrow();
	 String asyncerror_loadrowscount();
	 String asyncerror_loadrows();
	 
	 String error_emptyrowisnull();
	 String error_rowisnull();
	 
	 String error_datasource_cant_be_null();
	 String error_not_authorized();
	 String error_cant_show_search_form();
}

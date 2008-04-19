package net.pleso.demo.client.localization;

/**
 * Constants for I18N.
 */
public interface Constants extends com.google.gwt.i18n.client.Constants {
	
  String admin_panel_caption();	

  String reference_books_menu();
  String user_menu();
  String login();
  String logout();
  String password();
  String login_form_caption();
  String save_password();
  
  String session_is_expired();
  
  String delete();

  //bank
  String banks();
  String add_bank();
  String edit_bank();
  String bank_name();
  String bank_mfo();
  
  String operation_id();
  String bank_id();
  String bank_name_verbose();
  String operation_date();
  String operation_type();
  String operation_description();
  String operation_is_confirmed();
  
  String add_operation();
  String edit_operation();
  String operations();
  
  String cl_id();
  String cl_name();
  String cl_name_verbose();
  String cl_bank_id();
  String cl_birthday();
  String cl_birthday_start();
  String cl_birthday_end();
  String cl_money();
  String cl_money_start();
  String cl_money_end();
  String cl_sex();
  String add_client();
  String edit_client();
  String client_search();
  String clients();
  String cl_money_period();
  String cl_birthday_period();
}

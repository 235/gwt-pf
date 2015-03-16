Export from https://code.google.com/p/gwt-pf/

# GWT-PF
GWT Pleso Framework is a high-level framework based on Google Web Toolkit (GWT) for creating visual AJAX database front-end user interfaces. It's some a kind of a complex CRUD framework.

GWT Pleso Framework enables you to create automatically generated user interface based on business-logic classes.

It provides control panel solutions for:

   * database enterprise projects;
   * other relation-based projects.
   * web-site management;

It has a set of components for manipulating user data such as DataGrid, DataTextBox, DataComboBox etc.

# Project highlights
    
   * clear, Java interfaces based model
   * customizable automatically generated user forms
   * user-input validation system
   * flexible support for related data (child forms)
   * authentication, authorization system
   * error handling
   * replaceable windows manager
   * localization system
   * paged data view
   * text, numeric, date, time, enumeration, selection data controls

# Screenshots
Screen from the [demo app](http://gwt.org.ua/en/demo-app/) - validation in the "add" form, more - in our [screnshots gallery](http://gwt.org.ua/en/gallery/gwt-pf-demo-screenshots/):

![](http://gwt.org.ua/media/images/2007/08/25/iviewcapture_date_19_08_2007_time_23_24_34-thumb.jpg)

----

# [Overview of GWT application architecture based on GWT-PF](http://gwt.org.ua/en/documentation/architecture/)

## Typical example of GWT application architecture

One of the classical approaches in the software design is a three-level architecture, where higher levels depend on lower:
 1. presentation layer - user interface (UI);
 1. business logic layer (BL);
 1. data access layer (DAL).

In GWT application those layers can be shared between client and server as follows:
 1. On client-side:
   * presentation layer
   * business logic layer
   * data access interfaces (GWT-RPC Client), data classes
 1. On server-server:
   * data access layer implementation (GWT-RPC server)
   * database

The scheme below clearly shows the dependence between packages of each layer on client and server sides.

_Component scheme of GWT application architecture based on GWT-PF. Source code allocation example (click on scheme to view full size image)._
![](http://gwt.org.ua/media/images/2007-11/gwt-pf-diagram_overview_en.jpg)

 
## GWT-PF role in GWT application

A typical requirement for information systems is the development of a large number of similar reference books and forms for data manipulation. These elements are presentation layer components. There are two general approaches to develop them:

 1. Creating of separate classes which represents visual component for each form.
 1. Creating universal visual components that can represent any form of the system.

The obvious is the fact that the first variant more labor for a large system.

gwt-pf-ui package is a set of universal visual components, which can be used to display almost any reference books and forms. So when building a system it is possible to use those components, significantly reducing the resources for the development of a presentation layer.

Visual components in gwt-pf-ui works with the business logic via interfaces of gwt-pf-core package. The implementation of business logic and data access layer of the application ill be presented by set of classes implementing interfaces from gwt-pf-core.

 
## Rapid development with GWT-PF

It is obvious that gwt-pf-ui is profitable in terms of code reuse and mostly implemented presentation layer. Moreover the existence of business logic interface with gwt-pf-core applies tested business logic development methodology.

# Links
 
 * [gwt-pf download](http://gwt.org.ua/en/download/)
 * [gwt-pf demo](http://gwt.org.ua/en/demo-app/)
 * [gwt-pf documentation](http://gwt.org.ua/en/documentation/)
 * [gwt-pf screenshots](http://gwt.org.ua/en/gallery/gwt-pf-demo-screenshots/)
 * [Netbeans+MySQL port](http://gwt.org.ua/en/blog/2009/07/15/gwt-pf-mysql-netbeans/)

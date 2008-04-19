--
-- PostgreSQL database dump
--

-- Started on 2008-04-18 12:39:36

SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

--
-- TOC entry 1646 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON SCHEMA public IS 'Standard public schema';


SET search_path = public, pg_catalog;

--
-- TOC entry 18 (class 1255 OID 65024)
-- Dependencies: 5 281
-- Name: search_add_bigint_parameter(character varying, bigint, character varying, character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION search_add_bigint_parameter(param_name character varying, param_value bigint, start_sql character varying, result_sql character varying) RETURNS character varying
    AS $$
DECLARE
    --	
BEGIN
    IF result_sql = start_sql THEN
	RETURN result_sql || ' WHERE ' || quote_ident(param_name) || ' = ' || cast(param_value as varchar);
    ELSE
	RETURN result_sql || ' AND ' || quote_ident(param_name) || ' = ' || cast(param_value as varchar);
    END IF;
END;
$$
    LANGUAGE plpgsql STABLE;


--
-- TOC entry 19 (class 1255 OID 65025)
-- Dependencies: 5 281
-- Name: search_add_bool_parameter(character varying, boolean, character varying, character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION search_add_bool_parameter(param_name character varying, param_value boolean, start_sql character varying, result_sql character varying) RETURNS character varying
    AS $$
DECLARE
    param_str varchar;	
BEGIN
    IF param_value = TRUE THEN
	param_str := 'TRUE';
    ELSE
	param_str := 'FALSE';
    END IF;

    IF result_sql = start_sql THEN
	RETURN result_sql || ' WHERE ' || quote_ident(param_name) || ' IS ' || param_str;
    ELSE
	RETURN result_sql || ' AND ' || quote_ident(param_name) || ' IS ' || param_str;
    END IF;
END;
$$
    LANGUAGE plpgsql STABLE;


--
-- TOC entry 20 (class 1255 OID 65026)
-- Dependencies: 281 5
-- Name: search_add_int_parameter(character varying, integer, character varying, character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION search_add_int_parameter(param_name character varying, param_value integer, start_sql character varying, result_sql character varying) RETURNS character varying
    AS $$
DECLARE
    --	
BEGIN
    IF result_sql = start_sql THEN
	RETURN result_sql || ' WHERE ' || quote_ident(param_name) || ' = ' || cast(param_value as varchar);
    ELSE
	RETURN result_sql || ' AND ' || quote_ident(param_name) || ' = ' || cast(param_value as varchar);
    END IF;
END;
$$
    LANGUAGE plpgsql STABLE;


--
-- TOC entry 21 (class 1255 OID 65027)
-- Dependencies: 281 5
-- Name: search_add_like_parameter(character varying, anyelement, character varying, character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION search_add_like_parameter(param_name character varying, param_value anyelement, start_sql character varying, result_sql character varying) RETURNS character varying
    AS $$
DECLARE
    -- declare	
BEGIN
    IF result_sql = start_sql THEN
	RETURN result_sql || ' WHERE ' || quote_ident(param_name) || ' ILIKE ''%' || cast(param_value as varchar) || '%''';
    ELSE
	RETURN result_sql || ' AND ' || quote_ident(param_name) || ' ILIKE ''%' || cast(param_value as varchar) || '%''';
    END IF;
END;
$$
    LANGUAGE plpgsql STABLE;


--
-- TOC entry 23 (class 1255 OID 65028)
-- Dependencies: 5 281
-- Name: search_add_numeric_range_parameter(character varying, numeric, numeric, character varying, character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION search_add_numeric_range_parameter(param_name character varying, param_value_start numeric, param_value_end numeric, start_sql character varying, result_sql character varying) RETURNS character varying
    AS $$
BEGIN
    IF result_sql = start_sql THEN
	RETURN result_sql || ' WHERE ' || quote_ident(param_name) || ' BETWEEN ' || cast(param_value_start as varchar) || ' and ' || cast(param_value_end as varchar);
    ELSE
	RETURN result_sql || ' AND ' || quote_ident(param_name) || ' BETWEEN ' || cast(param_value_start as varchar) || ' and ' || cast(param_value_end as varchar);
    END IF;
END;
$$
    LANGUAGE plpgsql STABLE;


--
-- TOC entry 24 (class 1255 OID 65029)
-- Dependencies: 5 281
-- Name: search_add_time_range_parameter(character varying, time without time zone, time without time zone, character varying, character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION search_add_time_range_parameter(param_name character varying, param_value_start time without time zone, param_value_end time without time zone, start_sql character varying, result_sql character varying) RETURNS character varying
    AS $$
BEGIN
    IF result_sql = start_sql THEN
	RETURN result_sql || ' WHERE ' || quote_ident(param_name) || ' BETWEEN ''' || to_char(param_value_start, 'HH24:MI') || ''' and ''' || to_char(param_value_end, 'HH24:MI') || '''';
    ELSE
	RETURN result_sql || ' AND ' || quote_ident(param_name) || ' BETWEEN ''' || to_char(param_value_start, 'HH24:MI') || ''' and ''' || to_char(param_value_end, 'HH24:MI') || '''';
    END IF;
END;
$$
    LANGUAGE plpgsql STABLE;


--
-- TOC entry 25 (class 1255 OID 65030)
-- Dependencies: 5 281
-- Name: search_add_timestamp_parameter(character varying, timestamp without time zone, character varying, character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION search_add_timestamp_parameter(param_name character varying, param_value timestamp without time zone, start_sql character varying, result_sql character varying) RETURNS character varying
    AS $$
DECLARE
    --	
BEGIN
    IF result_sql = start_sql THEN
	RETURN result_sql || ' WHERE ' || quote_ident(param_name) || ' LIKE ''%' || to_char(param_value, 'YYYY-MM-DD') || '%''';
    ELSE
	RETURN result_sql || ' AND ' || quote_ident(param_name) || ' LIKE ''%' || to_char(param_value, 'YYYY-MM-DD') || '%''';
    END IF;
END;
$$
    LANGUAGE plpgsql STABLE;


--
-- TOC entry 26 (class 1255 OID 65031)
-- Dependencies: 5 281
-- Name: search_add_timestamp_range_parameter(character varying, timestamp without time zone, timestamp without time zone, character varying, character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION search_add_timestamp_range_parameter(param_name character varying, param_value_start timestamp without time zone, param_value_end timestamp without time zone, start_sql character varying, result_sql character varying) RETURNS character varying
    AS $$
BEGIN
    IF result_sql = start_sql THEN
	RETURN result_sql || ' WHERE ' || quote_ident(param_name) || ' BETWEEN ''' || to_char(param_value_start, 'YYYY-MM-DD') || ''' and ''' || to_char(param_value_end, 'YYYY-MM-DD') || '''';
    ELSE
	RETURN result_sql || ' AND ' || quote_ident(param_name) || ' BETWEEN ''' || to_char(param_value_start, 'YYYY-MM-DD') || ''' and ''' || to_char(param_value_end, 'YYYY-MM-DD') || '''';
    END IF;
END;
$$
    LANGUAGE plpgsql STABLE;


--
-- TOC entry 1286 (class 1259 OID 65032)
-- Dependencies: 5
-- Name: bank_id_sequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE bank_id_sequence
    INCREMENT BY 1
    NO MAXVALUE
    MINVALUE 0
    CACHE 1;


--
-- TOC entry 1648 (class 0 OID 0)
-- Dependencies: 1286
-- Name: bank_id_sequence; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('bank_id_sequence', 460, true);


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1287 (class 1259 OID 65034)
-- Dependencies: 1625 5
-- Name: bank; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE bank (
    bank_id integer DEFAULT nextval('bank_id_sequence'::regclass) NOT NULL,
    bank_mfo integer NOT NULL,
    bank_name character varying(255) NOT NULL
);


--
-- TOC entry 27 (class 1255 OID 65037)
-- Dependencies: 5 263 281
-- Name: search_bank(character varying); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION search_bank(bank_name character varying) RETURNS SETOF bank
    AS $$DECLARE
    item bank%ROWTYPE;
    trim_bank_name varchar;
    sql varchar;
BEGIN
    trim_bank_name := trim(bank_name);
    IF bank_name is NULL or bank_name = '' THEN 
        sql := 'SELECT * FROM bank';
    ELSE
	sql := 'SELECT * FROM bank WHERE bank_name ILIKE ''' || trim_bank_name || '%''';
    END IF;
    --RAISE NOTICE 'SQL is %', sql;
    FOR item IN EXECUTE sql LOOP
        RETURN NEXT item;
    END LOOP;
    RETURN;
END;
$$
    LANGUAGE plpgsql;


--
-- TOC entry 1288 (class 1259 OID 65038)
-- Dependencies: 5
-- Name: client_id_sequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE client_id_sequence
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


--
-- TOC entry 1649 (class 0 OID 0)
-- Dependencies: 1288
-- Name: client_id_sequence; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('client_id_sequence', 63, true);


--
-- TOC entry 1289 (class 1259 OID 65040)
-- Dependencies: 1626 5
-- Name: client; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE client (
    cl_id integer DEFAULT nextval('client_id_sequence'::regclass) NOT NULL,
    cl_name character varying NOT NULL,
    cl_bank_id integer NOT NULL,
    cl_birthday timestamp without time zone NOT NULL,
    cl_money numeric NOT NULL,
    cl_sex integer NOT NULL
);


--
-- TOC entry 1290 (class 1259 OID 65046)
-- Dependencies: 1368 5
-- Name: client_view; Type: VIEW; Schema: public; Owner: -
--

CREATE VIEW client_view AS
    SELECT client.cl_id, client.cl_name, client.cl_bank_id, (SELECT bank.bank_name FROM bank WHERE (bank.bank_id = client.cl_bank_id)) AS bank_name, client.cl_birthday, client.cl_money, client.cl_sex FROM client;


--
-- TOC entry 28 (class 1255 OID 65049)
-- Dependencies: 281 5 276
-- Name: search_client(integer, character varying, integer, character varying, timestamp without time zone, timestamp without time zone, numeric, numeric, integer); Type: FUNCTION; Schema: public; Owner: -
--

CREATE FUNCTION search_client(cl_id integer, cl_name character varying, cl_bank_id integer, bank_name character varying, cl_birthday_start timestamp without time zone, cl_birthday_end timestamp without time zone, cl_money_start numeric, cl_money_end numeric, cl_sex integer) RETURNS SETOF client_view
    AS $$DECLARE
    item client_view%ROWTYPE;
    result_sql varchar;
    start_sql varchar;
BEGIN
    start_sql := 'SELECT * FROM client_view';
    result_sql := 'SELECT * FROM client_view';

   -- integer field  
    IF cl_id IS NOT NULL THEN 
        result_sql := search_add_int_parameter('cl_id', cl_id, start_sql, result_sql); 
    END IF;
   -- varchar field  
    IF cl_name IS NOT NULL THEN 
        result_sql := search_add_like_parameter('cl_name', cl_name, start_sql, result_sql); 
    END IF;
   -- integer field  
    IF cl_bank_id IS NOT NULL THEN 
        result_sql := search_add_int_parameter('cl_bank_id', cl_bank_id, start_sql, result_sql); 
    END IF;
   -- varchar field  
    IF bank_name IS NOT NULL THEN 
        result_sql := search_add_like_parameter('bank_name', bank_name, start_sql, result_sql); 
    END IF;
   -- timestamp field RANGE 
    IF cl_birthday_start IS NOT NULL AND cl_birthday_start IS NOT NULL THEN
        result_sql := search_add_timestamp_range_parameter('cl_birthday', cl_birthday_start, cl_birthday_end, start_sql, result_sql); 
    END IF;
   -- numeric field RANGE 
    IF cl_money_start IS NOT NULL AND cl_money_start IS NOT NULL THEN
        result_sql := search_add_numeric_range_parameter('cl_money', cl_money_start, cl_money_end, start_sql, result_sql); 
    END IF;
   -- integer field  
    IF cl_sex IS NOT NULL THEN 
        result_sql := search_add_int_parameter('cl_sex', cl_sex, start_sql, result_sql); 
    END IF;

    -- RAISE NOTICE 'SQL is %', result_sql;
    FOR item IN EXECUTE result_sql LOOP
        RETURN NEXT item;
    END LOOP;
    RETURN;
END;
$$
    LANGUAGE plpgsql;


--
-- TOC entry 1291 (class 1259 OID 65050)
-- Dependencies: 5
-- Name: auth_right; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE auth_right (
    class_name character varying(255) NOT NULL
);


--
-- TOC entry 1292 (class 1259 OID 65052)
-- Dependencies: 5
-- Name: operation_id_sequence; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE operation_id_sequence
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


--
-- TOC entry 1650 (class 0 OID 0)
-- Dependencies: 1292
-- Name: operation_id_sequence; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('operation_id_sequence', 522, true);


--
-- TOC entry 1293 (class 1259 OID 65054)
-- Dependencies: 1627 1628 5
-- Name: operation; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE operation (
    operation_id integer DEFAULT nextval('operation_id_sequence'::regclass) NOT NULL,
    bank_id integer NOT NULL,
    operation_date date NOT NULL,
    operation_type integer NOT NULL,
    operation_description text,
    op_cl_id integer,
    is_confirmed boolean DEFAULT false
);


--
-- TOC entry 1642 (class 0 OID 65050)
-- Dependencies: 1291
-- Data for Name: auth_right; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO auth_right (class_name) VALUES ('net.pleso.postgres.client.bl.bank.EF.AddBankEF');
INSERT INTO auth_right (class_name) VALUES ('net.pleso.postgres.client.bl.bank.EF.EditBankEF');
INSERT INTO auth_right (class_name) VALUES ('net.pleso.postgres.client.bl.bank.BankRB$RowDeleter');
INSERT INTO auth_right (class_name) VALUES ('net.pleso.postgres.client.bl.bank.BankRB$BankRBDataSource');
INSERT INTO auth_right (class_name) VALUES ('net.pleso.postgres.client.bl.operation.EF.AddOperationEF');
INSERT INTO auth_right (class_name) VALUES ('net.pleso.postgres.client.bl.operation.EF.EditOperationEF');
INSERT INTO auth_right (class_name) VALUES ('net.pleso.postgres.client.bl.operation.OperationRB$OperationRBDataSource');
INSERT INTO auth_right (class_name) VALUES ('net.pleso.postgres.client.bl.client.EF.AddClientEF');
INSERT INTO auth_right (class_name) VALUES ('net.pleso.postgres.client.bl.client.EF.EditClientEF');
INSERT INTO auth_right (class_name) VALUES ('net.pleso.postgres.client.bl.client.ClientRB$RowDeleter');
INSERT INTO auth_right (class_name) VALUES ('net.pleso.postgres.client.bl.client.ClientRB$ClientRBDataSource');
INSERT INTO auth_right (class_name) VALUES ('net.pleso.postgres.client.bl.client.EditClientEF$ClientOperationsProvider');
INSERT INTO auth_right (class_name) VALUES ('net.pleso.postgres.client.bl.operation.OperationByClientRB$OperationRBDataSource');


--
-- TOC entry 1640 (class 0 OID 65034)
-- Dependencies: 1287
-- Data for Name: bank; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO bank (bank_id, bank_mfo, bank_name) VALUES (93, 300335, 'Aval Bank');
INSERT INTO bank (bank_id, bank_mfo, bank_name) VALUES (94, 300335, 'Raiffeisen Bank Aval');
INSERT INTO bank (bank_id, bank_mfo, bank_name) VALUES (95, 321228, 'Ukrprom Bank');
INSERT INTO bank (bank_id, bank_mfo, bank_name) VALUES (96, 322948, 'Forum Bank');
INSERT INTO bank (bank_id, bank_mfo, bank_name) VALUES (97, 322755, 'CJSC РІР‚СљFUIBРІР‚Сњ');
INSERT INTO bank (bank_id, bank_mfo, bank_name) VALUES (98, 280101703, 'Banca Sociala');
INSERT INTO bank (bank_id, bank_mfo, bank_name) VALUES (99, 280101609, 'Banca de Economii');
INSERT INTO bank (bank_id, bank_mfo, bank_name) VALUES (100, 280101868, 'Banca Comerciala Romana');
INSERT INTO bank (bank_id, bank_mfo, bank_name) VALUES (101, 280101701, 'Victoriabank');
INSERT INTO bank (bank_id, bank_mfo, bank_name) VALUES (102, 280101716, 'Eurocreditbank');
INSERT INTO bank (bank_id, bank_mfo, bank_name) VALUES (103, 280101881, 'Investprivatbank');
INSERT INTO bank (bank_id, bank_mfo, bank_name) VALUES (104, 280101745, 'Comertbank');
INSERT INTO bank (bank_id, bank_mfo, bank_name) VALUES (105, 280101749, 'Mobiasbanca');
INSERT INTO bank (bank_id, bank_mfo, bank_name) VALUES (106, 280101325, 'Moldindconbank');
INSERT INTO bank (bank_id, bank_mfo, bank_name) VALUES (107, 280101723, 'Moldova Agroindbank');
INSERT INTO bank (bank_id, bank_mfo, bank_name) VALUES (108, 280101856, 'Unibank');
INSERT INTO bank (bank_id, bank_mfo, bank_name) VALUES (109, 280101803, 'Universalbank');
INSERT INTO bank (bank_id, bank_mfo, bank_name) VALUES (110, 280101735, 'Fincombank');
INSERT INTO bank (bank_id, bank_mfo, bank_name) VALUES (111, 280101793, 'Eximbank');
INSERT INTO bank (bank_id, bank_mfo, bank_name) VALUES (112, 280101845, 'Energbank');
INSERT INTO bank (bank_id, bank_mfo, bank_name) VALUES (113, 10100000, 'Narodowy Bank Polski');
INSERT INTO bank (bank_id, bank_mfo, bank_name) VALUES (114, 11400000, 'BRE Bank SA');
INSERT INTO bank (bank_id, bank_mfo, bank_name) VALUES (115, 14700002, 'Euro Bank SA');
INSERT INTO bank (bank_id, bank_mfo, bank_name) VALUES (116, 17900001, 'Calyon Bank Polska SA');
INSERT INTO bank (bank_id, bank_mfo, bank_name) VALUES (117, 21300004, 'VOLKSWAGEN BANK POLSKA SA');
INSERT INTO bank (bank_id, bank_mfo, bank_name) VALUES (118, 21400007, 'Fiat Bank Polska SA');
INSERT INTO bank (bank_id, bank_mfo, bank_name) VALUES (120, 19500001, 'GMAC Bank Polska SA');
INSERT INTO bank (bank_id, bank_mfo, bank_name) VALUES (119, 22900003, 'Dexia Kommunalkredit Bank Polska SA');


--
-- TOC entry 1641 (class 0 OID 65040)
-- Dependencies: 1289
-- Data for Name: client; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO client (cl_id, cl_name, cl_bank_id, cl_birthday, cl_money, cl_sex) VALUES (3, 'Tom Ridik', 101, '2007-05-05 00:00:00', 1000, 1);
INSERT INTO client (cl_id, cl_name, cl_bank_id, cl_birthday, cl_money, cl_sex) VALUES (4, 'Inna Gorobchuk', 109, '2005-04-06 00:00:00', 3050, 1);


--
-- TOC entry 1643 (class 0 OID 65054)
-- Dependencies: 1293
-- Data for Name: operation; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO operation (operation_id, bank_id, operation_date, operation_type, operation_description, op_cl_id, is_confirmed) VALUES (470, 99, '2007-05-03', 1, 'test 4', 3, false);
INSERT INTO operation (operation_id, bank_id, operation_date, operation_type, operation_description, op_cl_id, is_confirmed) VALUES (471, 116, '2007-05-31', 1, NULL, 4, false);
INSERT INTO operation (operation_id, bank_id, operation_date, operation_type, operation_description, op_cl_id, is_confirmed) VALUES (464, 117, '2007-05-31', 4, 'test2', 3, true);
INSERT INTO operation (operation_id, bank_id, operation_date, operation_type, operation_description, op_cl_id, is_confirmed) VALUES (473, 93, '2007-09-01', 1, NULL, 3, NULL);


--
-- TOC entry 1634 (class 2606 OID 65062)
-- Dependencies: 1291 1291
-- Name: auth_right_pk; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY auth_right
    ADD CONSTRAINT auth_right_pk PRIMARY KEY (class_name);


--
-- TOC entry 1630 (class 2606 OID 65064)
-- Dependencies: 1287 1287
-- Name: bank_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY bank
    ADD CONSTRAINT bank_pkey PRIMARY KEY (bank_id);


--
-- TOC entry 1632 (class 2606 OID 65066)
-- Dependencies: 1289 1289
-- Name: client_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY client
    ADD CONSTRAINT client_pkey PRIMARY KEY (cl_id);


--
-- TOC entry 1636 (class 2606 OID 65068)
-- Dependencies: 1293 1293
-- Name: operation_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY operation
    ADD CONSTRAINT operation_pkey PRIMARY KEY (operation_id);


--
-- TOC entry 1637 (class 2606 OID 65069)
-- Dependencies: 1287 1629 1289
-- Name: client_bank_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY client
    ADD CONSTRAINT client_bank_fkey FOREIGN KEY (cl_bank_id) REFERENCES bank(bank_id);


--
-- TOC entry 1638 (class 2606 OID 65074)
-- Dependencies: 1629 1287 1293
-- Name: opreation_bank_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY operation
    ADD CONSTRAINT opreation_bank_fkey FOREIGN KEY (bank_id) REFERENCES bank(bank_id);


--
-- TOC entry 1639 (class 2606 OID 65079)
-- Dependencies: 1289 1293 1631
-- Name: opreation_client_fkey; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY operation
    ADD CONSTRAINT opreation_client_fkey FOREIGN KEY (op_cl_id) REFERENCES client(cl_id);


--
-- TOC entry 1647 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: -
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2008-04-18 12:39:36

--
-- PostgreSQL database dump complete
--


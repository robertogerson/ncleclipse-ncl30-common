/*******************************************************************************
 * Este arquivo Ã© parte da implementaÃ§Ã£o do ambiente de autoria em Nested 
 * Context Language - NCL Eclipse.
 * Direitos Autorais Reservados (c) 2007-2010 UFMA/LAWS (LaboratÃ³rio de Sistemas 
 * AvanÃ§ados da Web)
 *
 * Este programa Ã© software livre; vocÃª pode redistribuÃ­-lo e/ou modificÃ¡-lo sob
 * os termos da LicenÃ§a PÃºblica Geral GNU versÃ£o 2 conforme publicada pela Free 
 * Software Foundation.
 *
 * Este programa Ã© distribuÃ­do na expectativa de que seja Ãºtil, porÃ©m, SEM 
 * NENHUMA GARANTIA; nem mesmo a garantia implÃ­cita de COMERCIABILIDADE OU
 * ADEQUAÃ‡ÃƒO A UMA FINALIDADE ESPECÃ�FICA. Consulte a LicenÃ§a PÃºblica Geral do
 * GNU versÃ£o 2 para mais detalhes. VocÃª deve ter recebido uma cÃ³pia da LicenÃ§a
 * PÃºblica Geral do GNU versÃ£o 2 junto com este programa; se nÃ£o, escreva para a
 * Free Software Foundation, Inc., no endereÃ§o 59 Temple Street, Suite 330,
 * Boston, MA 02111-1307 USA.
 *
 * Para maiores informaÃ§Ãµes:
 * - ncleclipse@laws.deinf.ufma.br
 * - http://www.laws.deinf.ufma.br/ncleclipse
 * - http://www.laws.deinf.ufma.br
 *
 *******************************************************************************
 * This file is part of the authoring environment in Nested Context Language -
 * NCL Eclipse.
 * Copyright: 2007-2010 UFMA/LAWS (Laboratory of Advanced Web Systems), All
 * Rights Reserved.
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License version 2 as published by
 * the Free Software Foundation.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License version 2 for
 * more details. You should have received a copy of the GNU General Public 
 * License version 2 along with this program; if not, write to the Free 
 * Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 
 * 02110-1301, USA.
 *
 * For further information contact:
 * - ncleclipse@laws.deinf.ufma.br
 * - http://www.laws.deinf.ufma.br/ncleclipse
 * - http://www.laws.deinf.ufma.br
 *
 ******************************************************************************/
package br.ufma.deinf.laws.ncl;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is responsible for validating the basics data types of NCL.
 * 
 * @author Roberto Azevedo
 * 
 */
public class DataType {
	public static final int UNKNOWN = -1;
	public static final int ID = 0;
	public static final int STRING = 1;
	public static final int INTEGER = 2;
	public static final int DOUBLE = 3;
	public static final int COORDINATE = 4;
	public static final int TIME = 5;
	public static final int URI = 6;
	public static final int MIME_TYPE = 7;
	public static final int SIZE = 8;
	public static final int XMLNS = 9;
	public static final int QUANTITY = 10;
	public static final int XCONNECTOR = 11;
	public static final int BOOLEAN_OPERATOR = 12;
	public static final int SYNCHRONISM_OPERATOR = 13;
	public static final int INSTANCE = 14;
	public static final int DEVICE = 15; // validacaoo feita no semï¿½ntico (gera
	// um warning).
	public static final int COMPARATOR = 16;
	public static final int SIMPLEACTION_ROLE = 17;
	public static final int SIMPLECONDITION_ROLE = 18;
	public static final int COLOR = 19;
	public static final int EVENT = 20;
	public static final int TRANSITION = 21;
	public static final int KEY = 22;
	public static final int ACTION = 23;
	public static final int TRANSITION_TYPE = 24;
	public static final int TRANSITION_SUBTYPE = 25;
	public static final int DIRECTION = 26;
	public static final int BOOLEAN_VALUE = 27;
	public static final int PROPERTY_NAME = 28;
	public static final int ZINDEX = 29;
	public static final int FIT_VALUE = 30;
	public static final int SCROLL = 31;
	public static final int FONT_VARIANT = 32;
	public static final int FONT_WEIGHT = 33;
	public static final int PLAYER_LIFE = 34;
	public static final int PARAM_VALUES = 35;

	public static final int TRANSITION_BORDER_COLOR = 36;

	public static final int TIME2 = 37;
	
	public DataType() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param str
	 * @return
	 * @deprecated
	 */
	public static int getType(String str) {
		if (isInteger(str))
			return INTEGER;
		if (isDouble(str))
			return DOUBLE;
		if (isCoordinate(str))
			return COORDINATE;
		if (isId(str))
			return ID;
		if (isTime(str))
			return TIME;
		if (isUri(str))
			return URI;
		return STRING;
	}

	public static boolean isInteger(String str) {
		Pattern p = Pattern.compile("^[0-9]*$");
		Matcher m = p.matcher(str);

		return m.matches();
	}

	public static boolean isString(String str) {
		return true;
	}

	public static boolean isDouble(String str) {
		return true;
	}

	/*
	 * FORMATOS rectangle: left-x,top-y,right-x,bottom-y circle:
	 * center-x,center-y,radius polygon: x1,y1,x2,y2,...,xN,yN
	 * 
	 * Por enquanto os valores sÃ£o inteiros. Tem que melhorar. Xn e Yn tem que
	 * ter a mesma quantidade
	 */
	public static boolean isCoordinate(String str) {
		Pattern p = Pattern.compile("^[0-9]*(,[0-9]*){2,}");
		Matcher m = p.matcher(str);

		return m.matches();
	}

	// FORMATO: 9s, 90s, 11s
	// Tem minuto e hora tambÃ©m???
	public static boolean isTime(String str) {
		Pattern p = Pattern.compile("^((([01]?[0-9])|2[0-3])[:])?([0-5][0-9][:])?([0-5][0-9])$");
		//Pattern p = Pattern.compile("^((([0-1]?[0-9])|([2][0-3])[:])([0-5]?[0-9])[:])([0-5]?[0-9])([.][0-9]+)$");
		// para nao reconhecer apenas numeros
		Pattern p2 = Pattern.compile("^[0-9]*$");
		// para reconhecer em segundos
		Pattern p3 = Pattern.compile("^[0-9]+([.][0-9]+)?[s]$");
		Matcher m = p.matcher(str);
		Matcher m2 = p2.matcher(str);
		Matcher m3 = p3.matcher(str);
		return ( m.matches() && !m2.matches() ) || m3.matches();
	}

	public static boolean isTime2(String str) {
		return isTime (str);
	}
	
	public static boolean isUri(String str) {
		try {
			// URL url = new URL(str);
			URI uri = new URI(URLEncoder.encode(str, "UTF-8"));
			return true;
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	// Conjunto de letras ou numeros comeÃ§ando com uma letra ou underline
	// TODO: Talvez o . deva ser substituÃ­do por caracteres especÃ­ficos.
	// Verificar na especificaÃ§Ã£o XML.
	public static boolean isId(String str) {
		Pattern p = Pattern.compile("^[_a-zA-Z].*$");
		Matcher m = p.matcher(str);

		return m.matches();
	}

	// 20px, 20, 20%
	public static boolean isSize(String str) {
		if (isInteger(str))
			return true;
		Pattern p = Pattern.compile("^[0-9]*px$");
		Matcher m = p.matcher(str);

		if (m.matches())
			return true;

		p = Pattern.compile("^[0-9]*[.]?[0-9]*[%]$");
		m = p.matcher(str);
		return m.matches();
	}

	public static boolean isMIME_TYPE(String value) {
		/*
		 * Vector<String> values =
		  AttributeValues.getValues(DataType.MIME_TYPE);
		  if (values.contains(value))
		  return true;
		 */

		// validation is in semantics
		return true;
	}

	public static boolean isXMLNS(String value) {
		return (value.equals("http://www.ncl.org.br/NCL3.0/EDTVProfile")
				|| value.equals("http://www.ncl.org.br/NCL3.0/BDTVProfile")
				|| value
						.equals("http://www.ncl.org.br/NCL3.0/CausalConnectorProfile") || value
				.equals("http://www.ncl.org.br/NCL3.0/LanguageProfile"));
	}

	public static boolean isInstance(String value) {
		return value.equals("new") || value.equals("instSame")
				|| value.equals("gradSame");
	}

	public static boolean isComparator(String value) {
		Vector<String> values = AttributeValues.getValues(DataType.COMPARATOR);
		if (values.contains(value))
			return true;
		return false;
	}

	public static boolean isDataType(int dataType, String value) {
		boolean ok = true;
		switch (dataType) {
			case DataType.COORDINATE:
				if (!DataType.isCoordinate(value))
					ok = false;
				break;
			case DataType.INTEGER:
				if (!DataType.isInteger(value))
					ok = false;
				break;
			case DataType.STRING:
				if (!DataType.isString(value))
					ok = false;
				break;
			case DataType.DOUBLE:
				if (!DataType.isDouble(value))
					ok = false;
				break;
			case DataType.ID:
				if (!DataType.isId(value))
					ok = false;
				break;
			case DataType.TIME:
				if (!DataType.isTime(value))
					ok = false;
				break;
			case DataType.TIME2:
				if (!DataType.isTime2(value))
					ok = false;
				break;
			case DataType.URI:
				if (!DataType.isUri(value))
					ok = false;
				break;
			case DataType.MIME_TYPE:
				if (!DataType.isMIME_TYPE(value))
					ok = false;
				break;
			case DataType.SIZE:
				if (!DataType.isSize(value))
					ok = false;
				break;
			case DataType.XMLNS:
				if (!DataType.isXMLNS(value))
					ok = false;
				break;
			case DataType.QUANTITY:
				if (!DataType.isQuantity(value))
					ok = false;
				break;
			case DataType.XCONNECTOR:
				if (!DataType.isXConnector(value))
					ok = false;
				break;
			case DataType.BOOLEAN_OPERATOR:
				if (!DataType.isBoleanOperator(value))
					ok = false;
				break;
			case DataType.SYNCHRONISM_OPERATOR:
				if (!DataType.isSyncronismOperator(value))
					ok = false;
				break;
			case DataType.INSTANCE:
				if (!DataType.isInstance(value))
					ok = false;
				break;
			case DataType.COMPARATOR:
				if (!DataType.isComparator(value))
					ok = false;
				break;
			case DataType.COLOR:
				if (!DataType.isColor(value))
					ok = false;
				break;
			case DataType.EVENT:
				if (!DataType.isEvent(value))
					ok = false;
				break;
			case DataType.TRANSITION:
				if (!DataType.isTransition(value))
					ok = false;
				break;
			case DataType.KEY:
				if (!DataType.isKey(value))
					ok = false;
				break;
			case DataType.ACTION:
				if (!DataType.isAction(value))
					ok = false;
				break;
			case DataType.TRANSITION_TYPE:
				if (!DataType.isTransitionType(value))
					ok = false;
				break;
			case DataType.DIRECTION:
				if (!DataType.isDirection(value))
					ok = false;
				break;
			case DataType.TRANSITION_SUBTYPE:
				if (!DataType.isTransitionSubType(value))
					ok = false;
				break;
			case DataType.BOOLEAN_VALUE:
				if (!DataType.isBooleanValue(value))
					ok = false;
				break;
			case DataType.PROPERTY_NAME:
				if (!DataType.isPropertyNameValue(value))
					ok = false;
				break;
			case DataType.ZINDEX:
				if (!DataType.isZIndex(value))
					ok = false;
				break;
			case DataType.TRANSITION_BORDER_COLOR:
				if (!DataType.isTransitionBorderColor(value))
					ok = false;
				break;
		}
		return ok;
	}

	/**
	 * @param value
	 * @return
	 */
	private static boolean isTransitionBorderColor(String value) {
		Vector<String> values = AttributeValues
				.getValues(DataType.TRANSITION_BORDER_COLOR);
		if (values.contains(value))
			return true;
		return false;
	}

	/**
	 * @param value
	 * @return
	 */
	private static boolean isPropertyNameValue(String value) {
		return isId(value);
	}

	private static boolean isBooleanValue(String value) {
		Vector<String> values = AttributeValues
				.getValues(DataType.BOOLEAN_VALUE);
		if (values.contains(value))
			return true;
		return false;
	}

	private static boolean isDirection(String value) {
		Vector<String> values = AttributeValues.getValues(DataType.DIRECTION);
		if (values.contains(value))
			return true;
		return false;
	}

	private static boolean isTransitionSubType(String value) {
		// TODO: validate based on transition type
		/* 	Vector<String>  values = AttributeValues.
				getValues(DataType.TRANSITION_SUBTYPE);
			if (values.contains(value))
			return true;
			return false;
		 */
		return isId(value);
	}

	private static boolean isTransitionType(String value) {
		Vector<String> values = AttributeValues
				.getValues(DataType.TRANSITION_TYPE);
		if (values.contains(value))
			return true;
		return false;
	}

	private static boolean isAction(String value) {
		Vector<String> values = AttributeValues.getValues(DataType.ACTION);
		if (values.contains(value))
			return true;
		return false;
	}

	private static boolean isKey(String value) {
		return true;
	}

	private static boolean isTransition(String value) {
		Vector<String> values = AttributeValues.getValues(DataType.TRANSITION);
		if (values.contains(value))
			return true;
		return false;
	}

	private static boolean isEvent(String value) {
		Vector<String> values = AttributeValues.getValues(DataType.EVENT);
		if (values.contains(value))
			return true;
		return false;
	}

	private static boolean isColor(String value) {
		Vector<String> values = AttributeValues.getValues(DataType.COLOR);
		if (values.contains(value))
			return true;
		return false;
	}

	private static boolean isSyncronismOperator(String value) {
		return value.equals("par") || value.equals("seq");
	}

	private static boolean isBoleanOperator(String value) {
		return value.equals("and") || value.equals("or");
	}

	private static boolean isXConnector(String value) {
		return true;
	}

	private static boolean isQuantity(String value) {
		try {
			Integer t = new Integer(value);
			if (t.intValue() < 1)
				return false;
		} catch (Exception e) {
			// TODO: handle exception
			return value.equals("unbounded");
		}
		return true;
	}

	private static boolean isZIndex(String value) {
		try {
			Integer t = new Integer(value);
			if (t.intValue() < 0 || t.intValue() > 255)
				return false;
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}

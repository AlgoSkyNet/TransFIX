/*
 * Copyright 2013 Peter Lawrey
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.openhft.fix.include.v42;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import net.openhft.fix.include.util.FixConstants;

public class FixMessage implements FixMessageInterface 
{
	//protected Header header;
    //protected Messages messages;
    //protected Trailer trailer;
    //protected Components components;
    //protected Fields fields;
    
    protected int major=4;
    protected int minor=2;
    protected int servicepack=0;
    protected CharSequence type="FIX";
    private StringBuilder fixMsgOutput;
    private char delim = '|';
    private char equalsChar = '=';
    private Field[] field;
    
    /*public FixMessage(FIXMessageBuilder fixMsgBuilder){
    	  this.header=fixMsgBuilder.getHeader();
    	  this.messages=fixMsgBuilder.getMessages();
    	  this.trailer=fixMsgBuilder.getTrailer();
    	  this.components=fixMsgBuilder.getComp();
    	  this.fields=fixMsgBuilder.getFields();
    	  this.fixMsgOutput = new StringBuilder();
    }*/
    
    public FixMessage(FIXMessageBuilder fixMsgBuilder) {
    	
	}
    
    public FixMessage(Field[] field){
    	this.field = field;
    }

	/*public Header getHeader() {
        return header;
    }

    public Messages getMessages() {
        return messages;
    }

     public Trailer getTrailer() {
        return trailer;
    }
  
    public Components getComponents() {
        return components;
    }

    public Fields getFields() {
        return fields;
    }*/

    public int getMajor() {
        return major;
    }

    public int getMinor() {
        return minor;
    }

    public int getServicepack() {
        return servicepack;
    }

    public CharSequence getType() {
        return type;
    }
    
    public Field getField(int fieldLocation){
    	System.out.println(fieldLocation +"====="+FixConstants.fieldsNumber[FixConstants.fieldsNumber.length-1]);
    	if (fieldLocation <= FixConstants.fieldsNumber[FixConstants.fieldsNumber.length-1]){
    		return this.field[fieldLocation-1];
    	}
    	
    	return null;
    }
    
    public String getFixString(){//depends on your business logic
    	//8|9|35|34
    	this.fixMsgOutput = new StringBuilder();
    	this.fixMsgOutput.setLength(0);
    	this.fixMsgOutput.append(FixConstants.fieldsNumber[7]);
    	this.fixMsgOutput.append(this.type);
    	this.fixMsgOutput.append(this.equalsChar);
    	this.fixMsgOutput.append(this.major);
    	this.fixMsgOutput.append(this.minor);
    	this.fixMsgOutput.append(this.servicepack);
    	this.fixMsgOutput.append(this.delim);
    	if (field != null)
    	{
	    	for (int i=0;i<field.length;i++)
	    	{
	    		this.fixMsgOutput.append(field[i].getNumber());
	    		this.fixMsgOutput.append(field[i].getName());
	    		this.fixMsgOutput.append(this.equalsChar);
	    		this.fixMsgOutput.append(this.delim);
	    	}
    	}
       	return this.fixMsgOutput.toString();
    }

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
	    /*out.writeObject(header);
	    out.writeObject(messages);
	    out.writeObject(trailer);
	    out.writeObject(components);
	    out.writeObject(fields);*/
		out.writeInt(major);
		out.writeInt(minor);
		out.writeInt(servicepack);
		out.writeUTF((String)type);
		out.writeObject(this.fixMsgOutput);
		out.writeChar(delim);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException 
	{
		/*header = (Header) in.readObject();
	    messages= (Messages) in.readObject();
	    trailer= (Trailer) in.readObject();
	    components= (Components) in.readObject();
	    fields= (Fields) in.readObject();*/
		major= in.readInt();
		minor= in.readInt();
		servicepack= in.readInt();
		type = in.readUTF();
		this.fixMsgOutput= (StringBuilder) in.readObject();
		delim= in.readChar();
		
	}

}

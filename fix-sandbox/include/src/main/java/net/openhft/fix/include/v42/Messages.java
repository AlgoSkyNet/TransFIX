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

import net.openhft.lang.collection.HugeArray;
import net.openhft.lang.collection.HugeCollections;

public class Messages 
{
    protected HugeArray<Message> messageArr;
    protected int messageSize;   
    protected int fieldSize;
    protected int groupSize;
    
    
    public Messages setMessagesSize(int messageSize){
    	this.messageSize=messageSize;
    	return this;
    }
    
    public Messages setFieldSize(int fieldSize){this.fieldSize=fieldSize;return this;}
    public Messages setGroupSize(int groupSize){this.groupSize=groupSize;return this;}
        
    public HugeArray<Message> getMessage() {
        if (messageArr == null) {
            messageArr = HugeCollections.newArray(Message.class, messageSize);
            for (int i=0;i<messageSize;i++)
            {
            	Message message = messageArr.get(i);
            	message.setFieldSize(fieldSize);
            	message.setGroupSize(groupSize);
            }
        }
        return this.messageArr;
    }

}

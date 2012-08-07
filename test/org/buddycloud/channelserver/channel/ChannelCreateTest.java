/*
 * Copyright 2011 buddycloud
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.buddycloud.channelserver.channel;

import junit.framework.Assert;

import org.buddycloud.channelserver.ChannelServerTestHelper;
import org.jivesoftware.smack.packet.Packet;
import org.junit.Test;

/**
 * @author Lloyd Watkin <lloyd.watkn@surevine.com>
 *
 */
public class ChannelCreateTest extends ChannelServerTestHelper {

	@Test
	public void testDiscoveryInfo() throws Exception {
		
		Packet packet = getPacket("resources/channel/channel-create.request");
		Packet reply = sendPacket(packet);
		System.out.println('---  create channel ---');
		System.out.println(packet);
        System.out.println(reply);
		Assert.assertEquals(packet.getPacketID(), getValue(reply, "/iq/@id"));
		Assert.assertTrue(exists(reply, "/iq/query/identity[@type='channels' and @category='pubsub']"));
	}
	
	@Test
	public void testDiscoveryItems() throws Exception {
		
		Packet packet = getPacket("resources/discovery/discovery-item.request");
		Packet reply = sendPacket(packet);
		
		Assert.assertEquals(packet.getPacketID(), getValue(reply, "/iq/@id"));
		Assert.assertTrue(exists(reply, "/iq/query/item"));
		Assert.assertTrue(exists(reply, "/iq/query/item[1]/@jid"));
		Assert.assertTrue(exists(reply, "/iq/query/item[1]/@node"));
	}
	
}

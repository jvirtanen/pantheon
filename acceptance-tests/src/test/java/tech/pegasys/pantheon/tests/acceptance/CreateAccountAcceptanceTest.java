/*
 * Copyright 2018 ConsenSys AG.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package tech.pegasys.pantheon.tests.acceptance;

import static org.web3j.utils.Convert.Unit.ETHER;

import tech.pegasys.pantheon.tests.acceptance.dsl.AcceptanceTestBase;
import tech.pegasys.pantheon.tests.acceptance.dsl.account.Account;
import tech.pegasys.pantheon.tests.acceptance.dsl.node.Node;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class CreateAccountAcceptanceTest extends AcceptanceTestBase {

  private Node minerNode;

  @Before
  public void setUp() throws Exception {
    minerNode = pantheon.createMinerNode("node1");
    cluster.start(minerNode, pantheon.createArchiveNode("node2"));
  }

  @Test
  public void shouldCreateAnAccount() {
    final Account account = accounts.createAccount("a-new-account");
    minerNode.execute(transactions.createTransfer(account, 20));
    cluster.verify(account.balanceEquals("20", ETHER));
  }
}

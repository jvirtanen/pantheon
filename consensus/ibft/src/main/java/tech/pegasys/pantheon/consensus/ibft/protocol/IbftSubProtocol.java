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
package tech.pegasys.pantheon.consensus.ibft.protocol;

import tech.pegasys.pantheon.consensus.ibft.ibftmessage.IbftV2;
import tech.pegasys.pantheon.ethereum.p2p.wire.Capability;
import tech.pegasys.pantheon.ethereum.p2p.wire.SubProtocol;

public class IbftSubProtocol implements SubProtocol {

  public static String NAME = "IBF";
  public static final Capability IBFV1 = Capability.create(NAME, 1);

  private static final IbftSubProtocol INSTANCE = new IbftSubProtocol();

  public static IbftSubProtocol get() {
    return INSTANCE;
  }

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public int messageSpace(final int protocolVersion) {
    return IbftV2.MESSAGE_SPACE;
  }

  @Override
  public boolean isValidMessageCode(final int protocolVersion, final int code) {
    switch (code) {
      case IbftV2.PRE_PREPARE:
      case IbftV2.PREPARE:
      case IbftV2.COMMIT:
      case IbftV2.ROUND_CHANGE:
        return true;

      default:
        return false;
    }
  }
}

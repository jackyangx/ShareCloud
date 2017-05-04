package encryption;

import SecretCloudProxy.Ciphertext;
import it.unisa.dia.gas.jpbc.Element;

public class decryptTask {
	//解密信息m ：m * e(gA^r, g^s^xAt) / e(gA^s^xA, g^rt) = cipher.me/e(skA, g^rt)
	public static byte[] decryptMsg(encryptionModule module, Ciphertext cipher, Element sk) {
		Element grt = module.newG1ElementFromBytes(cipher.grt).getImmutable();
		Element eskgrt = module.e(sk, grt);
		Element me = module.newGTElementFromBytes(cipher.me).getImmutable();
		Element m = me.duplicate().div(eskgrt).duplicate().getImmutable();
		return m.toBytes();
	}
}

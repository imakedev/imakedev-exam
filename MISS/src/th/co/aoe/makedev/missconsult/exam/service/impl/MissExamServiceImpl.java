package th.co.aoe.makedev.missconsult.exam.service.impl;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.List;

import org.apache.commons.codec.binary.Hex;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import th.co.aoe.makedev.missconsult.constant.ServiceConstant;
import th.co.aoe.makedev.missconsult.exam.service.MissExamService;
import th.co.aoe.makedev.missconsult.xstream.MissExamGroup;
import th.co.aoe.makedev.missconsult.xstream.common.Pagging;
import th.co.aoe.makedev.missconsult.xstream.common.VResultMessage;
 
public class MissExamServiceImpl extends PostCommon implements MissExamService {
	// MISS_EXAM_GROUP
	@Override
	public Long saveMissExamGroup(MissExamGroup missExamGroup) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateMissExamGroup(MissExamGroup missExamGroup) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMissExamGroup(MissExamGroup missExamGroup) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MissExamGroup findMissExamGroupById(Long megId) {
		// TODO Auto-generated method stub
		MissExamGroup missExamGroup = new MissExamGroup();
		missExamGroup.setMegId(megId);
		missExamGroup.setServiceName(ServiceConstant.MISS_EXAM_GROUP_FIND_BY_ID);
		VResultMessage resultMessage = postMessage(missExamGroup,missExamGroup.getClass().getName(),"missExamGroup",true);
		return (MissExamGroup)resultMessage.getResultListObj().get(0); 
	}

	@Override
	public List searchMissExamGroup(MissExamGroup missExamGroup, Pagging pagging) {
		// TODO Auto-generated method stub
		return null;
	}
//	private static ResourceBundle bundle;
	/*static{
		  //bundle =  ResourceBundle.getBundle( "org/restlet/example/book/restlet/ch8/mailApplication" );
		//bundle =  ResourceBundle.getBundle( "sendmail" );				
	}*/
    // BPS_GROUP
/*	public int saveBpsGroup(BpsGroup bpsGroup) {
		bpsGroup.setServiceName(ServiceConstant.BPS_GROUP_SAVE);
		VResultMessage resultMessage=postMessage(bpsGroup,bpsGroup.getClass().getName(),"bpsGroups/",true);
		bpsGroup = (BpsGroup)resultMessage.getResultListObj().get(0);
		return bpsGroup.getUpdateRecord();
	}

	public int updateBpsGroup(BpsGroup bpsGroup) {
		bpsGroup.setServiceName(ServiceConstant.BPS_GROUP_UPDATE);
		VResultMessage resultMessage =postMessage(bpsGroup,bpsGroup.getClass().getName(),"bpsGroups/",true);
		bpsGroup = (BpsGroup)resultMessage.getResultListObj().get(0);
		return bpsGroup.getUpdateRecord();
	}

	public int deleteBpsGroup(String key) {
		BpsGroup bpsGroup = new BpsGroup();
		bpsGroup.setBpgId(Long.parseLong(key));
		bpsGroup.setServiceName(ServiceConstant.BPS_GROUP_DELETE);
		VResultMessage resultMessage =postMessage(bpsGroup,bpsGroup.getClass().getName(),"bpsGroups/",true);
		bpsGroup = (BpsGroup)resultMessage.getResultListObj().get(0);
		return bpsGroup.getUpdateRecord();
	}

	public BpsGroup findBpsGroupById(String bpgId) {
		BpsGroup bpsGroup = new BpsGroup();
		bpsGroup.setBpgId(new Long(bpgId));
		bpsGroup.setServiceName(ServiceConstant.BPS_GROUP_FIND_BY_ID);
		VResultMessage resultMessage = postMessage(bpsGroup,bpsGroup.getClass().getName(),"bpsGroups/",true);
		return (BpsGroup)resultMessage.getResultListObj().get(0);

	}

	public VResultMessage searchBpsGroup(BpsGroup bpsGroup) {
		bpsGroup.setServiceName(ServiceConstant.BPS_GROUP_SEARCH);
		return postMessage(bpsGroup,bpsGroup.getClass().getName(),"bpsGroups/",true);

	}
	public int checkDuplicateGroup(String groupName) {
		BpsGroup bpsGroup = new BpsGroup();
		bpsGroup.setBpgGroupName(groupName);
		bpsGroup.setServiceName(ServiceConstant.BPS_GROUP_CHECK_DUPLICATE);
		VResultMessage resultMessage =postMessage(bpsGroup,bpsGroup.getClass().getName(),"bpsGroups/",true);
		bpsGroup = (BpsGroup)resultMessage.getResultListObj().get(0);
		return bpsGroup.getUpdateRecord();
	}*/
	public static void main(String[] args) {
		/*MissExamServiceImpl impl =new MissExamServiceImpl();
		MissExamGroup missExamGroup=impl.findMissExamGroupById(new Long(1));
		System.out.println(missExamGroup);*/
		StandardPasswordEncoder encode =new StandardPasswordEncoder();
		System.out.println(encode.encode("koala"));
		System.out.println(encode.matches(encode.encode("koala"), "4efe081594ce25ee4efd9f7067f7f678a347bccf2de201f3adf2a3eb544850b465b4e51cdc3fcdde"));
		String sessionid="aoe";
        
		byte[] defaultBytes = sessionid.getBytes();
		try{
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			algorithm.reset();
			algorithm.update(defaultBytes);
			byte messageDigest[] = algorithm.digest();
		            
			StringBuffer hexString = new StringBuffer();
			for (int i=0;i<messageDigest.length;i++) {
				hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
			}
			String foo = messageDigest.toString();
			System.out.println("sessionid "+sessionid+" md5 version is "+hexString.toString());
			sessionid=hexString+"";
		}catch(NoSuchAlgorithmException nsae){
		            nsae.printStackTrace();
		}
	//	Security.addProvider(new BouncyCastleProvider());

		String data = "55";

		MessageDigest mda=null;
		try {
			mda = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte [] digesta = mda.digest(data.getBytes());

		MessageDigest mdb=null;
		try {
			mdb = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte [] digestb = mdb.digest(data.getBytes());

		System.out.println(MessageDigest.isEqual(digesta, digestb));

		System.out.println(Hex.encodeHex(digesta));

	}
   
}

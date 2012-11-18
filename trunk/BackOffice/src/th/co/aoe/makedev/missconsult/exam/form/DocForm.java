package th.co.aoe.makedev.missconsult.exam.form;

import java.io.Serializable;

import th.co.aoe.makedev.missconsult.xstream.MissDoc;

public class DocForm extends CommonForm implements Serializable {
		private static final long serialVersionUID = 1L;
		private MissDoc missDoc;
		public DocForm(){
			missDoc=new MissDoc();
		}
		public MissDoc getMissDoc() {
			return missDoc;
		}
		public void setMissDoc(MissDoc missDoc) {
			this.missDoc = missDoc;
		}
}

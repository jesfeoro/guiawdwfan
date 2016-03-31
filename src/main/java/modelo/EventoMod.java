package modelo;

import com.dhtmlx.planner.DHXEvent;

public class EventoMod extends DHXEvent {
	//public class EventoMod  {
		public String subject;
		public String readonly; 
		
	    public String getReadonly() {
	        return readonly;
	    }
	    public void setReadonly(String readonly) {
	        this.readonly = readonly;
	    }
		public String getSubject() {
			return subject;
		}

		public void setSubject(String subject) {
			this.subject = subject;
		}

}

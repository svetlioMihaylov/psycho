package com.interview.questions.beans;

import java.io.Serializable;

import com.interview.questions.enums.NotificationType;

public  class ResponseBean implements Serializable{
	
	private static final long serialVersionUID = 4397844151187101248L;

	public ResponseBean(String ressponseMessage, NotificationType notificationType, boolean cleanDiv) {
		
		this.ressponseMessage = ressponseMessage;
		this.notoficationType = notificationType;
		this.cleanDiv = cleanDiv;
	}
	
	private String ressponseMessage;
	
	private NotificationType notoficationType;
	
	private boolean cleanDiv;

	public String getRessponseMessage() {
		return ressponseMessage;
	}

	public void setRessponseMessage(String ressponseMessage) {
		this.ressponseMessage = ressponseMessage;
	}


	public NotificationType getNotoficationType() {
		return notoficationType;
	}

	public void setNotoficationType(NotificationType notoficationType) {
		this.notoficationType = notoficationType;
	}

	public boolean isCleanDiv() {
		return cleanDiv;
	}

	public void setCleanDiv(boolean cleanDiv) {
		this.cleanDiv = cleanDiv;
	}
	

}

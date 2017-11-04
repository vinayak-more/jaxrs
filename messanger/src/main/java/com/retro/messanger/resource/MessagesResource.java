package com.retro.messanger.resource;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.retro.messanger.model.Message;
import com.retro.messanger.model.MessageFilter;
import com.retro.messanger.service.MessageService;

@Path("/messages")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessagesResource {

	MessageService service = new MessageService();

	@GET
	public List<Message> getMessages(@BeanParam MessageFilter filter) {
		System.out.println("MessagesResource.getMessages() " + filter);
		return service.getAllMessages(filter);
	}

	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long id) {
		return service.getMessage(id);
	}

	@POST
	public Message addMessage(Message message) {
		return service.addMessage(message);
	}

	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long id,
			Message message) {
		message.setId(id);
		return service.updateMessage(message);
	}

	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") long id) {
		service.deleteMessage(id);
	}

	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}

}

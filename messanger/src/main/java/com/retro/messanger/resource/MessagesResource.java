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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

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
	public Message getMessage(@Context UriInfo uriInfo,
			@PathParam("messageId") long id) {
		Message message = service.getMessage(id);
		generateLinks(uriInfo, message);
		return message;
	}

	private void generateLinks(UriInfo uriInfo, Message message) {
		message.getLinks().clear();
		String selfUrl = uriInfo.getAbsolutePathBuilder().toString();
		String commentsUrl = getCommentsLink(uriInfo, message);
		message.addLink("self", selfUrl);
		message.addLink("comments", commentsUrl);
	}

	private String getCommentsLink(UriInfo uriInfo, Message message) {
		return uriInfo.getBaseUriBuilder().path(MessagesResource.class)
				.path(MessagesResource.class, "getCommentResource")
				.resolveTemplate("messageId", message.getId()).toString();
	}

	@POST
	public Response addMessage(@Context UriInfo uriInfo, Message message) {
		Message newMessage = service.addMessage(message);
		return Response
				.created(
						uriInfo.getAbsolutePathBuilder()
								.path(String.valueOf(newMessage.getId()))
								.build()).entity(newMessage).build();
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

package com.retro.messanger.resource;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.retro.messanger.model.Comment;
import com.retro.messanger.service.CommentService;

@Path("/")
public class CommentResource {

	private CommentService service = new CommentService();

	@GET
	public List<Comment> getAllComment(@PathParam("messageId") long messageId) {
		return service.getAllComments(messageId);
	}

	@GET
	@Path("/{commentId}")
	public Comment getComment(@PathParam("messageId") long messageId,
			@PathParam("commentId") long commentId) {
		return service.getComment(messageId, commentId);
	}

	@POST
	public Comment addComment(@PathParam("messageId") long messageId,
			Comment comment) {
		return service.addComment(messageId, comment);
	}

	@PUT
	@Path("/{commentId}")
	public Comment updateComment(@PathParam("messageId") long messageId,
			@PathParam("commentId") long commentId, Comment comment) {
		return service.updateComment(messageId, commentId, comment);
	}

	@DELETE
	@Path("/{commentId}")
	public Comment deleteComment(@PathParam("messageId") long messageId,
			@PathParam("commentId") long commentId) {
		return service.deleteComment(messageId, commentId);
	}
}

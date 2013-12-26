package com.nebeltv.ivawrapper.xmlparser.nodes;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import java.util.ArrayList;
import java.util.List;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

/**
 *
 * @author dst
 */
@XStreamAlias("entry")
public class Entry {

	@XStreamAlias("published")
	private String publishedDate;

	@XStreamAlias("content")
	private Content content;

	@XStreamAlias("author")
	private Author author;

	@XStreamImplicit(itemFieldName = "link")
	private List<Link> links = new ArrayList<Link>();

	/**
	 * @return the publishedDate
	 */
	public String getPublishedDate() {
		return publishedDate;
	}

	/**
	 * @param publishedDate the publishedDate to set
	 */
	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}

	/**
	 * @return the content
	 */
	public Content getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(Content content) {
		this.content = content;
	}

	/**
	 * @return the author
	 */
	public Author getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(Author author) {
		this.author = author;
	}

	public void addLink(Link link) {
		links.add(link);
	}

	/**
	 * @return the links
	 */
	public List<Link> getLinks() {
		return links;
	}

	/**
	 * @param links the links to set
	 */
	public void setLinks(List<Link> links) {
		this.links = links;
	}

	@Override
	public String toString() {
		return reflectionToString(this, SHORT_PREFIX_STYLE);
	}
}

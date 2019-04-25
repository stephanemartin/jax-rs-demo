package com.neotys.ntm.jaxrs.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableBook.class)
@JsonDeserialize(as = ImmutableBook.class)
public interface Book {
	String getTitle();
	String getDescription();
	int getPageNumber();
}

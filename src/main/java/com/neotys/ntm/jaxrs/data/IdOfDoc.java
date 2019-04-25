package com.neotys.ntm.jaxrs.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableIdOfDoc.class)
@JsonDeserialize(as = ImmutableIdOfDoc.class)
public interface IdOfDoc {
	Book getBook();
	int getId();
}

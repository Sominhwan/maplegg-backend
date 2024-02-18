package kr.co.maple.module.union.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CharacterUnionDTO {
	UnionDTO union;
	UnionArtifactDTO unionArtifact;
	UnionRaiderDTO unionRaider;
}

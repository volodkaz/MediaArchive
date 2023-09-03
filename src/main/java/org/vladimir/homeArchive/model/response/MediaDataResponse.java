package org.vladimir.homeArchive.model.response;

import java.util.List;

public record MediaDataResponse(Long id, List<MediaDataPropertyResponse> properties, String path) {
}

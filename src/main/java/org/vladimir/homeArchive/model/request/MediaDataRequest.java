package org.vladimir.homeArchive.model.request;

import java.util.List;

public record MediaDataRequest(Long id, List<MediaDataPropertyRequest> mediaProperties, String path) {
}

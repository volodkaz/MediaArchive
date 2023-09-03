package org.vladimir.homeArchive.model.object;

import java.util.List;

public record MediaData (Long id, List<MediaDataProperty> mediaProperties, String createDate, String modifyDate, String path)
{
}

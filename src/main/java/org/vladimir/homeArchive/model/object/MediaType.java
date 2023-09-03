package org.vladimir.homeArchive.model.object;

public record MediaType (
        Long id,
        String name,
        String pathToMedia,
        String createDate,
        String modifyDate)
{
}

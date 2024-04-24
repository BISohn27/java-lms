package nextstep.courses.domain.session.image;

import java.time.LocalDateTime;
import java.util.Objects;

import nextstep.common.BaseDateTime;

public class CoverImage extends BaseDateTime {

    private final Long id;
    private final ImageType type;
    private final Size size;
    private final Dimensions dimensions;

    public CoverImage(final ImageType type, final Size size, final Dimensions dimensions) {
        this(null, type, size, dimensions);
    }

    public CoverImage(final Long id, final ImageType type, final Size size, final Dimensions dimensions) {
        this.id = id;
        this.type = type;
        this.size = size;
        this.dimensions = dimensions;
    }

    public CoverImage(
            final Long id,
            final ImageType type,
            final Size size,
            final Dimensions dimensions,
            final LocalDateTime createdAt,
            final LocalDateTime updatedAt
    ) {
        super(createdAt, updatedAt);

        this.id = id;
        this.type = type;
        this.size = size;
        this.dimensions = dimensions;
    }

    public Long id() {
        return this.id;
    }

    public String typeName() {
        return this.type.typeName();
    }

    public long size() {
        return this.size.bytes();
    }

    public int width() {
        return this.dimensions.width();
    }

    public int height() {
        return this.dimensions.height();
    }

    @Override
    public boolean equals(final Object otherCoverImage) {
        if (this == otherCoverImage) {
            return true;
        }

        if (otherCoverImage == null || getClass() != otherCoverImage.getClass()) {
            return false;
        }

        final CoverImage that = (CoverImage)otherCoverImage;

        return Objects.equals(id, that.id) &&
                type == that.type &&
                Objects.equals(size, that.size) &&
                Objects.equals(dimensions, that.dimensions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.type, this.size, this.dimensions);
    }
}

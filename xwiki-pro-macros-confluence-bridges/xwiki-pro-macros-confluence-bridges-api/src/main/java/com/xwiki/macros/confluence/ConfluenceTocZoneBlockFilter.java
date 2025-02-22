/*
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package com.xwiki.macros.confluence;

import java.util.List;

import org.xwiki.rendering.block.Block;
import org.xwiki.rendering.block.HeaderBlock;
import org.xwiki.rendering.block.PlainTextBlockFilter;
import org.xwiki.rendering.block.SpaceBlock;
import org.xwiki.rendering.parser.Parser;
import org.xwiki.rendering.renderer.reference.link.LinkLabelGenerator;

/**
 * Used to filter the {@link HeaderBlock} title to generate the toc anchor.
 *
 * @version $Id$
 * @since 1.25.4
 */
public class ConfluenceTocZoneBlockFilter extends PlainTextBlockFilter
{
    /**
     * @param plainTextParser a plain text parser used to transform link labels into plain text
     * @param linkLabelGenerator generate link label.
     * @since 2.0M3
     */
    public ConfluenceTocZoneBlockFilter(Parser plainTextParser, LinkLabelGenerator linkLabelGenerator)
    {
        super(plainTextParser, linkLabelGenerator);
    }

    /**
     * @param headerBlock the section title.
     * @return the filtered label to use in toc anchor link.
     */
    public List<Block> generateLabel(HeaderBlock headerBlock)
    {
        List<Block> children = headerBlock.clone(this).getChildren();
        if (children.isEmpty()) {
            // If the header is empty, explicitly assign a single space. Otherwise, the toc entry is created with the
            // name of the page.
            children = List.of(new SpaceBlock());
        }
        return children;
    }
}

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
package com.xwiki.macros.userprofile.internal.macro;

import java.lang.reflect.Type;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.xwiki.component.annotation.Component;
import org.xwiki.model.reference.DocumentReferenceResolver;
import org.xwiki.model.reference.EntityReferenceSerializer;
import org.xwiki.properties.converter.AbstractConverter;
import org.xwiki.text.StringUtils;

import com.xwiki.macros.userprofile.macro.UserReference;

/**
 * XWiki Properties Bean Converter to convert Strings into {@link com.xwiki.macros.userprofile.macro.UserReference}.
 *
 * @version $Id$
 * @see org.xwiki.properties.converter.Converter
 */
@Component
@Singleton
public class UserReferenceConverter extends AbstractConverter<UserReference>
{
    @Inject
    private DocumentReferenceResolver<String> referenceResolver;

    @Inject
    private EntityReferenceSerializer<String> serializer;

    @Override
    protected <G extends UserReference> G convertToType(Type targetType, Object value)
    {
        if (value != null) {
            String valueString = value.toString().trim();
            if (!StringUtils.isEmpty(valueString)) {
                return (G) new UserReference(referenceResolver.resolve(valueString));
            }
        }

        return null;
    }

    @Override
    protected String convertToString(UserReference value)
    {
        if (value == null) {
            return null;
        }
        return this.serializer.serialize(value);
    }
}

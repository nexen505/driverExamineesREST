<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                version="1.0">
    <xsl:output method="html" indent="yes"/>
    <xsl:template match="/">
        <html>
            <head>
                <style>
                    .content {
                    text-align: left;
                    display: flex;
                    flex-direction: column;
                    max-width: 600px;
                    }
                </style>
            </head>
            <body>
                <div class="content">
                    <xsl:apply-templates/>
                </div>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="owners">
        <xsl:for-each select="owner">
            <ul>
                <li>
                    <xsl:text>Id: </xsl:text>
                    <xsl:value-of select="id"/>
                </li>
                <li>
                    <xsl:value-of select="name"/>
                    <xsl:text> </xsl:text>
                    <xsl:value-of select="patronymic"/>
                    <xsl:text> </xsl:text>
                    <xsl:value-of select="surname"/>
                </li>
                <li>
                    <xsl:text>Дата рождения: </xsl:text>
                    <xsl:value-of select="dateOfBirth"/>
                </li>
                <li>
                    <xsl:text>Машины: </xsl:text>
                    <xsl:apply-templates select="vehicles"/>
                </li>
            </ul>
        </xsl:for-each>
    </xsl:template>
    <xsl:template match="typesOfVehicle">
        <xsl:for-each select="typeOfVehicle">
            <ul>
                <li>
                    <xsl:text>Id: </xsl:text>
                    <xsl:value-of select="id"/>
                </li>
                <li>
                    <xsl:text>Название: </xsl:text>
                    <xsl:value-of select="name"/>
                </li>
                <li>
                    <xsl:text>Марка: </xsl:text>
                    <xsl:value-of select="brand"/>
                </li>
                <li>
                    <xsl:text>Минимальный вес (т): </xsl:text>
                    <xsl:value-of select="minimumWeight"/>
                </li>
                <li>
                    <xsl:text>Максимальный вес (т): </xsl:text>
                    <xsl:value-of select="maximumWeight"/>
                </li>
            </ul>
        </xsl:for-each>
    </xsl:template>
    <xsl:template match="vehicles">
        <xsl:for-each select="vehicle">
            <ul>
                <li>
                    <xsl:text>Id: </xsl:text>
                    <xsl:value-of select="id"/>
                </li>
                <li>
                    <xsl:text>Название: </xsl:text>
                    <xsl:value-of select="name"/>
                </li>
                <li>
                    <xsl:text>Бренд: </xsl:text>
                    <xsl:value-of select="brand"/>
                </li>
                <li>
                    <xsl:text>Год выпуска: </xsl:text>
                    <xsl:value-of select="yearOfIssue"/>
                </li>
            </ul>
        </xsl:for-each>
    </xsl:template>
</xsl:stylesheet>
package com.esdsl;

/**
 * @author lyq
 * @date 2022/1/16 3:04
 */
public class DSLTemplete {

    public static final String CREATE_MAPPING = "{\n" +
            "  \"mappings\": {\n" +
            "    \"properties\": {\n" +
            "      \"info\": {\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"zhongwen_pinyin\",\n" +
            "        \"search_analyzer\": \"ik_max_word\"\n" +
            "      },\n" +
            "      \"info2\": {\n" +
            "        \"type\": \"keyword\"\n" +
            "      },\n" +
            "      \"email\": {\n" +
            "        \"type\": \"keyword\",\n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"number\": {\n" +
            "        \"type\": \"integer\"\n" +
            "      },\n" +
            "      \"title\": {\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_smart\"\n" +
            "      },\n" +
            "      \"name\": {\n" +
            "        \"type\": \"object\",\n" +
            "        \"properties\": {\n" +
            "          \"firstName\": {\n" +
            "            \"type\": \"keyword\"\n" +
            "          },\n" +
            "          \"lastName\": {\n" +
            "            \"type\": \"keyword\"\n" +
            "          }\n" +
            "        }\n" +
            "      },\n" +
            "      \"buquan\": {\n" +
            "        \"type\": \"completion\",\n" +
            "        \"analyzer\": \"buquan_zhongwen_pinyin\"\n" +
            "      }\n" +
            "    }\n" +
            "  },\n" +
            "  \"settings\": {\n" +
            "    \"analysis\": {\n" +
            "      \"analyzer\": {\n" +
            "        \"zhongwen_pinyin\": {\n" +
            "          \"tokenizer\": \"ik_max_word\",\n" +
            "          \"filter\": \"py\"\n" +
            "        },\n" +
            "        \"buquan_zhongwen_pinyin\": {\n" +
            "          \"tokenizer\": \"keyword\",\n" +
            "          \"filter\": \"py\"\n" +
            "        }\n" +
            "      },\n" +
            "      \"filter\": {\n" +
            "        \"py\": {\n" +
            "          \"type\": \"pinyin\",\n" +
            "          \"keep_full_pinyin\": false,\n" +
            "          \"keep_joined_full_pinyin\": true,\n" +
            "          \"keep_original\": true,\n" +
            "          \"limit_first_letter_length\": 16,\n" +
            "          \"remove_duplicated_term\": true,\n" +
            "          \"none_chinese_pinyin_tokenize\": false\n" +
            "        }\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}";

}

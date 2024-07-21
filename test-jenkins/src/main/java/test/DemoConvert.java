package test;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DemoConvert {

    DemoConvert INSTANCE = Mappers.getMapper(DemoConvert.class);


    Person convert(PersonReq vo);
}

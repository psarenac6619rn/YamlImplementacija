package sk.projekat.yaml_implementacija;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import sk.projekat.specifikacija.Entitet;
import sk.projekat.specifikacija.Specifikacija;

public class YamlImplementacija extends Specifikacija{

	@Override
	public void absOpenDatabase(boolean isNew, int fileCount) {
		if(isNew)
			return;
		this.read(fileCount);
		
	}

	@Override
	public void read(int fileNumber) {
		for(int i = 0; i < fileNumber; i++) {
			try {
				File file = new File(this.getDirectory().getAbsolutePath() + "/" + i + ".yaml");
				ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
				List<Entitet> ent = mapper.readValue(file, new TypeReference<List<Entitet>>() {});
				this.getEntiteti().addAll(ent);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println(this.getEntiteti());
		}
		
	}

	@Override
	public void write(int fileNumber) {
		for(int i = 0; i < this.getFileCount(); i++) {
			File file = new File(this.getDirectory().getAbsolutePath() + "/" + i + ".yaml");
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
				mapper.writeValue(file, this.getEntitetiForFile(i));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}

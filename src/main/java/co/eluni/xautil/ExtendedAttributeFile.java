package co.eluni.xautil;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ExtendedAttributeFile {
    protected File wrapped;

    public String getProcessOutput(String process, String @NotNull ... args)  {
        try {

            Runtime rt = Runtime.getRuntime();
            String[] commands = new String[1 + args.length];
            commands[0] = process;
            System.arraycopy(args, 0, commands, 1, args.length);
            Process proc = rt.exec(commands);

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String s;
            while ((s = stdInput.readLine()) != null) {
                sb.append(s);
                sb.append('\n');
            }
            return sb.toString();
        } catch (IOException e) {
            throw new RuntimeException("FATALITY!!");

        }
    }

    public ExtendedAttributeFile(@NotNull File wrapped) {
        this.wrapped = wrapped;
        name = new SimpleStringProperty(wrapped.getName());
        attributes = new SimpleObjectProperty<>(new ArrayList<>());
        String output = getProcessOutput("xattr", wrapped.getAbsolutePath());
        List<String> attrs = Arrays.stream(output.split("\n")).toList();
        attributes.getValue().addAll(attrs);
        attributes.addListener((observableValue, strings, t1) -> System.out.println("Need to update filesystem here"));
    }

    protected StringProperty name;
    protected ObservableValue<List<String>> attributes;

    public ObservableValue<List<String>> attributesProperty() {
        return attributes;
    }

    public File getWrapped() {
        return wrapped;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public Map<String, Byte[]> getAttributes() {
        throw new NotImplementedException();
    }

    public Set<String> getAttributeNames() {
        return getAttributes().keySet();
    }

    public byte[] getAttributeRaw(String rawNamed) {
        throw new NotImplementedException();
    }

    public byte[] getAttribute(String key, String... dnsComponents) {
        throw new NotImplementedException();
    }
}

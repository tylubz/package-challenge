package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.model.Package;
import com.mobiquityinc.utils.FileReaderUtil;
import com.mobiquityinc.utils.FormatterUtil;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Main class which is responsible for processing packages.
 */
public class Packer {

    /**
     * Private default constructor.
     */
    private Packer() {
    }

    /**
     * Processes file and returns a string with information about pack and selected items on each line.
     *
     * @param filePath path to the file
     * @return list of items numbers, separated by comma, which satisfies the conditions
     * @throws APIException if some unpredictable situation has happened during processing
     */
    public static String pack(String filePath) throws APIException {
        try (Stream<String> stringStream = FileReaderUtil.readFile(filePath)) {
            List<Package> packageList = stringStream.map(PackageParser::parse).collect(Collectors.toList());
            List<Package> processedPackages = packageList.stream()
                    .map(pkg -> new KnapsackProcessor(pkg).filter())
                    .collect(Collectors.toList());
            return FormatterUtil.formatPackages(processedPackages);
        }
    }
}

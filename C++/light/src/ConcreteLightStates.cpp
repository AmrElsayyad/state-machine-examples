#include "ConcreteLightStates.h"

void LightOff::toggle(Light* light)
{
	// Off -> Low
	light->setState(LowIntensity::getInstance());
}

const char *LightOff::toString() const {
	return "LightOff";
}

LightState& LightOff::getInstance()
{
	static LightOff singleton;
	return singleton;
}

void LowIntensity::toggle(Light* light)
{
	// Low -> Medium
	light->setState(MediumIntensity::getInstance());
}

const char *LowIntensity::toString() const {
	return "LowIntensity";
}

LightState& LowIntensity::getInstance()
{
	static LowIntensity singleton;
	return singleton;
}

void MediumIntensity::toggle(Light* light)
{
	// Medium -> High
	light->setState(HighIntensity::getInstance());
}

const char *MediumIntensity::toString() const {
	return "MediumIntensity";
}

LightState& MediumIntensity::getInstance()
{
	static MediumIntensity singleton;
	return singleton;
}

void HighIntensity::toggle(Light* light)
{
	// High -> Low
	light->setState(LightOff::getInstance());
}

const char *HighIntensity::toString() const {
	return "HighIntensity";
}

LightState& HighIntensity::getInstance()
{
	static HighIntensity singleton;
	return singleton;
}
